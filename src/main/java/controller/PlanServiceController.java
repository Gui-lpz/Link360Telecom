package controller;

import java.util.ArrayList;
import model.data.PlanServiceData;
import model.entities.Plan;

public class PlanServiceController {

    private PlanServiceData planData;

    public PlanServiceController() {
        planData = new PlanServiceData();
    }

    /**
     * Registrar plan tarifario
     */
    public void addPlan(Plan plan) throws Exception {
        planData.add(plan);
    }

    /**
     * Buscar plan por ID
     */
    public Plan findPlanById(int id) throws Exception {
        return planData.findById(id);
    }

    /**
     * Obtener todos los planes
     */
    public ArrayList<Plan> getAllPlans() throws Exception {
        return planData.getAll();
    }

    /**
     * Actualizar plan
     */
    public void updatePlan(Plan plan) throws Exception {
        planData.update(plan);
    }

    /**
     * Eliminar plan
     */
    public void deletePlan(int id) throws Exception {
        planData.delete(id);
    }
}