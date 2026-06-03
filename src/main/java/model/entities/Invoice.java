package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {

    private int id;
    private String invoiceNumber;
    private int clientId;
    private int lineId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private BigDecimal amount;
    private BigDecimal taxes;
    private BigDecimal appliedDiscounts;
    private int redeemedPoints;
    private BigDecimal finalAmount;
    private LocalDate paymentDate;
    private PaymentStatus paymentStatus;

    public Invoice() {
    }

    public Invoice(int id, String invoiceNumber, int clientId, int lineId, LocalDate invoiceDate, LocalDate dueDate, BigDecimal amount, BigDecimal taxes, BigDecimal appliedDiscounts, int redeemedPoints, BigDecimal finalAmount, LocalDate paymentDate, PaymentStatus paymentStatus) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.clientId = clientId;
        this.lineId = lineId;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.taxes = taxes;
        this.appliedDiscounts = appliedDiscounts;
        this.redeemedPoints = redeemedPoints;
        this.finalAmount = finalAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getAppliedDiscounts() {
        return appliedDiscounts;
    }

    public void setAppliedDiscounts(BigDecimal appliedDiscounts) {
        this.appliedDiscounts = appliedDiscounts;
    }

    public int getRedeemedPoints() {
        return redeemedPoints;
    }

    public void setRedeemedPoints(int redeemedPoints) {
        this.redeemedPoints = redeemedPoints;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invoiceNumber=" + invoiceNumber + ", clientId=" + clientId + ", lineId=" + lineId + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", amount=" + amount + ", taxes=" + taxes + ", appliedDiscounts=" + appliedDiscounts + ", redeemedPoints=" + redeemedPoints + ", finalAmount=" + finalAmount + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + '}';
    }

  
}