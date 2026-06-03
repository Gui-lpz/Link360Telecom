package controller;

import java.util.ArrayList;
import model.data.InvoiceData;
import model.entities.Invoice;

public class InvoiceController {

    private InvoiceData invoiceData;

    public InvoiceController() {
        invoiceData = new InvoiceData();
    }

    /**
     * Registrar factura
     */
    public void addInvoice(Invoice invoice) throws Exception {
        invoiceData.add(invoice);
    }

    /**
     * Buscar factura por ID
     */
    public Invoice findInvoiceById(int id) throws Exception {
        return invoiceData.findById(id);
    }

    /**
     * Obtener todas las facturas
     */
    public ArrayList<Invoice> getAllInvoices() throws Exception {
        return invoiceData.getAll();
    }

    /**
     * Obtener facturas de un cliente
     */
    public ArrayList<Invoice> getInvoicesByClient(int clientId) throws Exception {
        return invoiceData.findByClientId(clientId);
    }

    /**
     * Actualizar factura
     */
    public void updateInvoice(Invoice invoice) throws Exception {
        invoiceData.update(invoice);
    }

    /**
     * Eliminar factura
     */
    public void deleteInvoice(int id) throws Exception {
        invoiceData.delete(id);
    }

    /**
     * Registrar pago de factura
     */
    public void registerPayment(Invoice invoice) throws Exception {
        invoiceData.update(invoice);
    }
}