package model.entities;

import java.time.LocalDate;

public class LinePackageHistory {

    private int id;
    private int lineId;
    private int packageId;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;

    public LinePackageHistory() {
    }

    public LinePackageHistory(int id, int lineId, int packageId, LocalDate purchaseDate, LocalDate expirationDate) {
        this.id = id;
        this.lineId = lineId;
        this.packageId = packageId;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
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

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "LinePackageHistory{" + "id=" + id + ", lineId=" + lineId + ", packageId=" + packageId + ", purchaseDate=" + purchaseDate + ", expirationDate=" + expirationDate + '}';
    }

}