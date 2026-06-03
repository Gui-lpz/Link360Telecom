package controller;

import java.util.ArrayList;
import model.data.MobileLineData;
import model.entities.MobileLine;

public class MobileLineController {

    private MobileLineData mobileLineData;

    public MobileLineController() {
        mobileLineData = new MobileLineData();
    }

    /**
     * Registrar línea móvil
     */
    public void addMobileLine(MobileLine line) throws Exception {
        mobileLineData.add(line);
    }

    /**
     * Buscar línea por ID
     */
    public MobileLine findMobileLineById(int id) throws Exception {
        return mobileLineData.findById(id);
    }

    /**
     * Obtener todas las líneas
     */
    public ArrayList<MobileLine> getAllMobileLines() throws Exception {
        return mobileLineData.getAll();
    }

    /**
     * Obtener líneas de un cliente
     */
    public ArrayList<MobileLine> getLinesByClient(int clientId) throws Exception {
        return mobileLineData.findByClientId(clientId);
    }

    /**
     * Actualizar línea
     */
    public void updateMobileLine(MobileLine line) throws Exception {
        mobileLineData.update(line);
    }

    /**
     * Suspender línea
     */
    public void suspendLine(MobileLine line) throws Exception {
        mobileLineData.update(line);
    }

    /**
     * Cancelar línea
     */
    public void cancelLine(MobileLine line) throws Exception {
        mobileLineData.update(line);
    }

    /**
     * Cambiar SIM
     */
    public void changeSim(MobileLine line) throws Exception {
        mobileLineData.update(line);
    }

    /**
     * Eliminar línea
     */
    public void deleteMobileLine(int id) throws Exception {
        mobileLineData.delete(id);
    }
}