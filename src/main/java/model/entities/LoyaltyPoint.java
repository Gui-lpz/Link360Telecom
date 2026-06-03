package model.entities;

import java.time.LocalDate;

public class LoyaltyPoint {

    private int id;
    private int clientId;
    private LocalDate date;
    private int earnedPoints;
    private int redeemedPoints;
    private int availableBalance;
    private String description;

}