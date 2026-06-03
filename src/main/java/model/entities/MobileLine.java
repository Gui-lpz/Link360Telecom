package model.entities;

import java.time.LocalDate;

public class MobileLine {

    private int id;
    private String phoneNumber;
    private LineType lineType;
    private Technology technology;
    private LocalDate activationDate;
    private LineStatus status;
    private SimType simType;
    private int clientId;

    public MobileLine() {
    }

    public MobileLine(int id, String phoneNumber, LineType lineType,
                      Technology technology, LocalDate activationDate,
                      LineStatus status, SimType simType, int clientId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.lineType = lineType;
        this.technology = technology;
        this.activationDate = activationDate;
        this.status = status;
        this.simType = simType;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LineStatus getStatus() {
        return status;
    }

    public void setStatus(LineStatus status) {
        this.status = status;
    }

    public SimType getSimType() {
        return simType;
    }

    public void setSimType(SimType simType) {
        this.simType = simType;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "MobileLine{" + "id=" + id + ", phoneNumber=" + phoneNumber + ", lineType=" + lineType + ", technology=" + technology + ", activationDate=" + activationDate + ", status=" + status + ", simType=" + simType + ", clientId=" + clientId + '}';
    }
    

   
}