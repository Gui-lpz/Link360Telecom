package controller;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.data.ClientData;
import model.data.DbConnection_Link360Telecom;
import model.data.UserTelecomData;
import model.entities.Client;
import model.entities.ClientType;
import model.entities.UserRole;
import model.entities.UserTelecom;

@WebServlet("/client")
public class ClientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "register":
                response.sendRedirect("registro_cliente.jsp");
                break;
            case "edit":
                handleEditForm(request, response);
                break;
            case "delete":
                handleDelete(request, response);
                break;
            case "list":
                handleList(request, response);
                break;
            case "logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("index.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "register":
                handleRegister(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    // REGISTER — inserta en Client Y en UserTelecom (transacción)
    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String identification = request.getParameter("identification");
        String name = request.getParameter("name");
        String firstSurname = request.getParameter("firstSurname");
        String secondSurname = request.getParameter("secondSurname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String address = request.getParameter("address");
        String contactPhone = request.getParameter("contactPhone");

        if (isBlank(identification) || isBlank(name) || isBlank(firstSurname)
                || isBlank(email) || isBlank(password)) {
            request.setAttribute("error", "Completá todos los campos obligatorios.");
            forward(request, response, "registro_cliente.jsp");
            return;
        }

        if (!password.equals(password2)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            forward(request, response, "registro_cliente.jsp");
            return;
        }

        try {
            ClientData clientData = new ClientData();
            if (clientData.findByEmail(email.trim()) != null) {
                request.setAttribute("error", "Ya existe una cuenta con ese correo electrónico.");
                forward(request, response, "registro_cliente.jsp");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error técnico al verificar el correo.");
            forward(request, response, "registro_cliente.jsp");
            return;
        }

        // Construir entidades
        Client newClient = new Client(
                0,
                identification.trim(),
                name.trim(),
                firstSurname.trim(),
                secondSurname != null ? secondSurname.trim() : "",
                address != null ? address.trim() : "",
                email.trim(),
                contactPhone != null ? contactPhone.trim() : "",
                LocalDate.now(),
                ClientType.BRONCE
        );
        newClient.setPassword(password);

        // UserTelecom con rol CLIENT — usa el mismo email y password
        UserTelecom newUser = new UserTelecom();
        newUser.setUsername(name.trim() + " " + firstSurname.trim());
        newUser.setEmail(email.trim());
        newUser.setPassword(password);
        newUser.setRole(UserRole.CLIENT);

        // Insertar ambos en una transacción — si uno falla, ninguno se guarda
        try (Connection conn = DbConnection_Link360Telecom.getConnection()) {
            conn.setAutoCommit(false);
            try {
                ClientData clientData = new ClientData();
                UserTelecomData userTelecomData = new UserTelecomData();

                clientData.add(newClient, conn);
                userTelecomData.add(newUser, conn);

                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", "Error técnico al crear la cuenta: " + ex.getClass().getName());
                forward(request, response, "registro_cliente.jsp");
                return;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error de conexión. Intentá de nuevo.");
            forward(request, response, "registro_cliente.jsp");
            return;
        }

        response.sendRedirect("index.jsp?registered=true");
    }

    private void handleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ClientData clientData = new ClientData();
            ArrayList<Client> clients = clientData.getAll();
            request.setAttribute("clients", clients);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("clientError", "No se pudieron cargar los clientes.");
        }

        // TODO: redirigir al panel admin cuando exista
        response.sendRedirect("index.jsp");
    }

    private void handleEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ClientData clientData = new ClientData();
            Client client = clientData.findById(id);
            request.setAttribute("clientToEdit", client);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("clientError", "No se pudo cargar el cliente.");
        }

        // TODO: forward al formulario de edición del panel admin
        response.sendRedirect("index.jsp");
    }

    // UPDATE
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String identification = request.getParameter("identification");
            String name = request.getParameter("name");
            String firstSurname = request.getParameter("firstSurname");
            String secondSurname = request.getParameter("secondSurname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String contactPhone = request.getParameter("contactPhone");
            String clientTypeStr = request.getParameter("clientType");

            ClientData clientData = new ClientData();
            Client existing = clientData.findById(id);
            if (existing == null) {
                request.setAttribute("clientError", "Cliente no encontrado.");
                forward(request, response, "index.jsp");
                return;
            }

            existing.setIdentification(identification.trim());
            existing.setName(name.trim());
            existing.setFirstSurname(firstSurname.trim());
            existing.setSecondSurname(secondSurname != null ? secondSurname.trim() : "");
            existing.setEmail(email.trim());
            existing.setAddress(address != null ? address.trim() : "");
            existing.setContactPhone(contactPhone != null ? contactPhone.trim() : "");
            if (clientTypeStr != null) {
                existing.setClientType(ClientType.valueOf(clientTypeStr.toUpperCase()));
            }

            clientData.update(existing);
            response.sendRedirect("client?action=list");

        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("clientError", "Error al actualizar el cliente: " + ex.getMessage());
            forward(request, response, "index.jsp");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ClientData clientData = new ClientData();
            clientData.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("clientError", "Error al eliminar el cliente: " + ex.getMessage());
        }

        response.sendRedirect("client?action=list");
    }

    // Helpers
    private boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String path)
            throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, res);
    }
}
