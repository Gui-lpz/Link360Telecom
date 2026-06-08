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
    private String password;

    private ArrayList<MobileLine> mobileLines;

    public Client() {
        this.mobileLines = new ArrayList<>();
    }

    public Client(int id, String identification, String name, String firstSurname, String secondSurname, String address, String email, String contactPhone, LocalDate entryDate, ClientType clientType, String password, ArrayList<MobileLine> mobileLines) {
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
        this.password = password;
        this.mobileLines = mobileLines;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public ArrayList<MobileLine> getMobileLines() {
        return mobileLines;
    }

    public void setMobileLines(ArrayList<MobileLine> mobileLines) {
        this.mobileLines = mobileLines;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", identification=" + identification + ", name=" + name + ", firstSurname=" + firstSurname + ", secondSurname=" + secondSurname + ", address=" + address + ", email=" + email + ", contactPhone=" + contactPhone + ", entryDate=" + entryDate + ", clientType=" + clientType + ", mobileLines=" + mobileLines + '}';
    }

   
}