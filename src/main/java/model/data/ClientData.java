package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.Client;
import model.entities.ClientType;

public class ClientData {

    private Client map(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id"),
                rs.getString("identification"),
                rs.getString("name"),
                rs.getString("first_surname"),
                rs.getString("second_surname"),
                rs.getString("address"),
                rs.getString("email"),
                rs.getString("contact_phone"),
                rs.getDate("entry_date").toLocalDate(),
                ClientType.valueOf(rs.getString("client_type"))
        );
    }

    public void add(Client client) throws Exception {
        try (Connection conn = DbConnection_Link360Telecom.getConnection()) {
            add(client, conn);
        }
    }

    public void add(Client client, Connection conn) throws Exception {
        String sql = "INSERT INTO Client "
                + "(identification, name, first_surname, second_surname, address, "
                + " email, contact_phone, entry_date, client_type, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getIdentification());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getFirstSurname());
            stmt.setString(4, client.getSecondSurname());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getEmail());
            stmt.setString(7, client.getContactPhone());
            stmt.setDate(8, Date.valueOf(client.getEntryDate()));
            stmt.setString(9, client.getClientType().name());
            stmt.setString(10, client.getPassword());
            stmt.executeUpdate();
        }
    }

    public Client findById(int id) throws Exception {
        String sql = "SELECT * FROM Client WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public ArrayList<Client> getAll() throws Exception {
        ArrayList<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM Client";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public void update(Client client) throws Exception {
        String sql = "UPDATE Client SET identification=?, name=?, first_surname=?, second_surname=?, "
                + "address=?, email=?, contact_phone=?, entry_date=?, client_type=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getIdentification());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getFirstSurname());
            stmt.setString(4, client.getSecondSurname());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getEmail());
            stmt.setString(7, client.getContactPhone());
            stmt.setDate(8, Date.valueOf(client.getEntryDate()));
            stmt.setString(9, client.getClientType().name());
            stmt.setInt(10, client.getId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Client WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Client findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM Client WHERE email = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public Client login(String email, String password) throws Exception {
        String sql = "SELECT * FROM Client WHERE email = ? AND password = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }
}