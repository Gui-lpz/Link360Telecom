package model.entities;

public class CommercialCategory {

    private int id;
    private String description;
    private String maxConnectionSpeed;

    public CommercialCategory() {
    }

    public CommercialCategory(int id, String description, String maxConnectionSpeed) {
        this.id = id;
        this.description = description;
        this.maxConnectionSpeed = maxConnectionSpeed;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxConnectionSpeed() {
        return maxConnectionSpeed;
    }

    public void setMaxConnectionSpeed(String maxConnectionSpeed) {
        this.maxConnectionSpeed = maxConnectionSpeed;
    }

    @Override
    public String toString() {
        return "CommercialCategory{" + "id=" + id + ", description=" + description + ", maxConnectionSpeed=" + maxConnectionSpeed + '}';
    }

}