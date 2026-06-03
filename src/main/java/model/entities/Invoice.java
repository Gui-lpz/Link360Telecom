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

  
}