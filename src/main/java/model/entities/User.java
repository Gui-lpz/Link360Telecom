package model.entities;

public class User {

    private int id;
    private String username;
    private String password;
    private UserRole role;
    private Integer clientId;

    public User() {
    }

    public User(int id, String username, String password,
                UserRole role, Integer clientId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.clientId = clientId;
    }

    public User(String username, String password,
                UserRole role, Integer clientId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return username + " (" + role + ")";
    }
}