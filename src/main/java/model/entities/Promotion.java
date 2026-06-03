package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Promotion {

    private int id;
    private String code;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal discountPercentage;
    private int promotionTypeId;

   
}