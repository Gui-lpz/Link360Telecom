package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {

    private int id;
    private String identification;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String address; 
    private String email;  
    private String contactPhone;
    private LocalDate entryDate;
    private ClientType clientType;

    private ArrayList<MobileLine> mobileLines;

    public Client() {
        this.mobileLines = new ArrayList<>();
    }

    public Client(int id, String identification, String name, String firstSurname,
                  String secondSurname, String address, String email,
                  String contactPhone, LocalDate entryDate, ClientType clientType) {
        this.id = id;
        this.identification = identification;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.address = address;
        this.email = email;
        this.contactPhone = contactPhone;
        this.entryDate = entryDate;
        this.clientType = clientType;
        this.mobileLines = new ArrayList<>();
    }

   
}