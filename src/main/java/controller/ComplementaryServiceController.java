package controller;

import java.util.ArrayList;
import model.data.ComplementaryServiceData;
import model.entities.ComplementaryService;

public class ComplementaryServiceController {

    private ComplementaryServiceData serviceData;

    public ComplementaryServiceController() {
        serviceData = new ComplementaryServiceData();
    }

    /**
     * Registrar servicio
     */
    public void addService(ComplementaryService service) throws Exception {
        serviceData.add(service);
    }

    /**
     * Buscar servicio por ID
     */
    public ComplementaryService findServiceById(int id) throws Exception {
        return serviceData.findById(id);
    }

    /**
     * Obtener todos los servicios
     */
    public ArrayList<ComplementaryService> getAllServices() throws Exception {
        return serviceData.getAll();
    }

    /**
     * Actualizar servicio
     */
    public void updateService(ComplementaryService service) throws Exception {
        serviceData.update(service);
    }

    /**
     * Eliminar servicio
     */
    public void deleteService(int id) throws Exception {
        serviceData.delete(id);
    }
}