package model.entities;

import java.math.BigDecimal;

public class ComplementaryService {

    private int id;
    private String code;
    private String name;
    private String description;
    private BigDecimal monthlyCost;
    private String category;

    public ComplementaryService() {
    }

    public ComplementaryService(int id, String code, String name,
                                String description, BigDecimal monthlyCost,
                                String category) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.monthlyCost = monthlyCost;
        this.category = category;
    }

    // getters y setters
}