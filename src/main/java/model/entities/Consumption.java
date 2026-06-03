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


}