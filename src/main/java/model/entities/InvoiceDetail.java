package model.entities;

import java.math.BigDecimal;

public class InvoiceDetail {

    private int id;
    private int invoiceId;
    private String concept;
    private BigDecimal amount;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int id, int invoiceId, String concept, BigDecimal amount) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.concept = concept;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceDetail{" + "id=" + id + ", invoiceId=" + invoiceId + ", concept=" + concept + ", amount=" + amount + '}';
    }


}