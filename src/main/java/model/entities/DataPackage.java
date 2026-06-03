package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataPackage {

    private int id;
    private String code;
    private String name;
    private double capacityGB;
    private BigDecimal price;
    private int validityDays;

    public DataPackage() {
    }

    public DataPackage(int id, String code, String name, double capacityGB, BigDecimal price, int validityDays) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.capacityGB = capacityGB;
        this.price = price;
        this.validityDays = validityDays;
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

    public double getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(double capacityGB) {
        this.capacityGB = capacityGB;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(int validityDays) {
        this.validityDays = validityDays;
    }

    @Override
    public String toString() {
        return "DataPackage{" + "id=" + id + ", code=" + code + ", name=" + name + ", capacityGB=" + capacityGB + ", price=" + price + ", validityDays=" + validityDays + '}';
    }

 
}