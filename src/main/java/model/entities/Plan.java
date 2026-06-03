package model.entities;

import java.math.BigDecimal;

public class Plan {

    private int id;
    private String code;
    private String name;
    private String description;
    private BigDecimal monthlyFee;
    private double includedGB;
    private int includedMinutes;
    private int includedMessages;
    private BigDecimal excessCost;
    private int commercialCategoryId;


}