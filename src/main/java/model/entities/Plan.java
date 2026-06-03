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

    public Plan() {
    }

    public Plan(int id, String code, String name, String description, BigDecimal monthlyFee, double includedGB, int includedMinutes, int includedMessages, BigDecimal excessCost, int commercialCategoryId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.monthlyFee = monthlyFee;
        this.includedGB = includedGB;
        this.includedMinutes = includedMinutes;
        this.includedMessages = includedMessages;
        this.excessCost = excessCost;
        this.commercialCategoryId = commercialCategoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public double getIncludedGB() {
        return includedGB;
    }

    public void setIncludedGB(double includedGB) {
        this.includedGB = includedGB;
    }

    public int getIncludedMinutes() {
        return includedMinutes;
    }

    public void setIncludedMinutes(int includedMinutes) {
        this.includedMinutes = includedMinutes;
    }

    public int getIncludedMessages() {
        return includedMessages;
    }

    public void setIncludedMessages(int includedMessages) {
        this.includedMessages = includedMessages;
    }

    public BigDecimal getExcessCost() {
        return excessCost;
    }

    public void setExcessCost(BigDecimal excessCost) {
        this.excessCost = excessCost;
    }

    public int getCommercialCategoryId() {
        return commercialCategoryId;
    }

    public void setCommercialCategoryId(int commercialCategoryId) {
        this.commercialCategoryId = commercialCategoryId;
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", monthlyFee=" + monthlyFee + ", includedGB=" + includedGB + ", includedMinutes=" + includedMinutes + ", includedMessages=" + includedMessages + ", excessCost=" + excessCost + ", commercialCategoryId=" + commercialCategoryId + '}';
    }


}