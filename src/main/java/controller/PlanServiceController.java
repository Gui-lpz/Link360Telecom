package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.data.PlanServiceData;
import model.entities.Client;
import model.entities.Plan;
import model.entities.UserTelecom;

@WebServlet("/plan")
public class PlanServiceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request, response)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                handleList(request, response);
                break;
            case "edit":
                handleEditForm(request, response);
                break;
            case "delete":
                handleDelete(request, response);
                break;
            default:
                handleList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        if (!isLoggedIn(request, response)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                handleCreate(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            default:
                handleList(request, response);
        }
    }

    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            PlanServiceData planData = new PlanServiceData();
            ArrayList<Plan> plans = planData.getAllWithCategory();
            request.setAttribute("plans", plans);
        } catch (Exception ex) {
            Logger.getLogger(PlanServiceController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("planError", "No se pudieron cargar los planes.");
        }

        request.setAttribute("activeSection", "planes");
        request.getRequestDispatcher("cliente_portal.jsp").forward(request, response);
    }

    private void handleEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PlanServiceData planData = new PlanServiceData();
            Plan plan = planData.findById(id);
            request.setAttribute("planToEdit", plan);
        } catch (Exception ex) {
            Logger.getLogger(PlanServiceController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("planError", "No se pudo cargar el plan.");
        }

        // TODO: redirigir al formulario de edición del panel admin cuando exista
        // request.getRequestDispatcher("admin/plan_form.jsp").forward(request, response);
        handleList(request, response);
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Plan plan = buildPlanFromRequest(request);
            PlanServiceData planData = new PlanServiceData();
            planData.add(plan);
            response.sendRedirect("plan?action=list");
        } catch (Exception ex) {
            Logger.getLogger(PlanServiceController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("planError", "Error al crear el plan: " + ex.getMessage());
            handleList(request, response);
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Plan plan = buildPlanFromRequest(request);
            plan.setId(Integer.parseInt(request.getParameter("id")));
            PlanServiceData planData = new PlanServiceData();
            planData.update(plan);
            response.sendRedirect("plan?action=list");
        } catch (Exception ex) {
            Logger.getLogger(PlanServiceController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("planError", "Error al actualizar el plan: " + ex.getMessage());
            handleList(request, response);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PlanServiceData planData = new PlanServiceData();
            planData.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(PlanServiceController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("planError", "Error al eliminar el plan: " + ex.getMessage());
        }

        response.sendRedirect("plan?action=list");
    }

    // Helpers
    private Plan buildPlanFromRequest(HttpServletRequest request) {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        BigDecimal fee = new BigDecimal(request.getParameter("monthlyFee"));
        double gb = Double.parseDouble(request.getParameter("includedGB"));
        int minutes = Integer.parseInt(request.getParameter("includedMinutes"));
        int messages = Integer.parseInt(request.getParameter("includedMessages"));
        BigDecimal excess = new BigDecimal(request.getParameter("excessCost"));
        int categoryId = Integer.parseInt(request.getParameter("commercialCategoryId"));

        return new Plan(0, code, name, desc, fee, gb, minutes, messages, excess, categoryId);
    }

    private boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        UserTelecom client = (session != null) ? (UserTelecom) session.getAttribute("loggedUser") : null;
        if (client == null) {
            response.sendRedirect("index.jsp");
            return false;
        }
        return true;
    }
}
