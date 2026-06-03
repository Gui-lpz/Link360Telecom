package model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Consumption {

    private int id;
    private int lineId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private ConsumptionType consumptionType;
    private double quantity;
    private BigDecimal calculatedCost;
    private int scopeId;

    public Consumption() {
    }

    public Consumption(int id, int lineId, LocalDateTime startDateTime, LocalDateTime endDateTime, ConsumptionType consumptionType, double quantity, BigDecimal calculatedCost, int scopeId) {
        this.id = id;
        this.lineId = lineId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.consumptionType = consumptionType;
        this.quantity = quantity;
        this.calculatedCost = calculatedCost;
        this.scopeId = scopeId;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCalculatedCost() {
        return calculatedCost;
    }

    public void setCalculatedCost(BigDecimal calculatedCost) {
        this.calculatedCost = calculatedCost;
    }

    public int getScopeId() {
        return scopeId;
    }

    public void setScopeId(int scopeId) {
        this.scopeId = scopeId;
    }
    

    @Override
    public String toString() {
        return "Consumption{" + "id=" + id + ", lineId=" + lineId + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", consumptionType=" + consumptionType + ", quantity=" + quantity + ", calculatedCost=" + calculatedCost + ", scopeId=" + scopeId + '}';
    }


}