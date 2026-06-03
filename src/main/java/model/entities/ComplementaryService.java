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

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(BigDecimal monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ComplementaryService{" + "id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", monthlyCost=" + monthlyCost + ", category=" + category + '}';
    }

 
}