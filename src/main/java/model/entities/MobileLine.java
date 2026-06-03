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

   
}