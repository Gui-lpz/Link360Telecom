package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.UserRole;
import model.entities.UserTelecom;

public class UserTelecomData {

    private UserTelecom map(ResultSet rs) throws SQLException {
        UserTelecom user = new UserTelecom();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(null);
        user.setRole(UserRole.valueOf(rs.getString("role")));
        return user;
    }

    public void add(UserTelecom user) throws Exception {
        try (Connection conn = DbConnection_Link360Telecom.getConnection()) {
            add(user, conn);
        }
    }

    public void add(UserTelecom user, Connection conn) throws Exception {
        String sql = "INSERT INTO UserTelecom (username, email, password, role) "
                   + "VALUES (?, ?, HASHBYTES('SHA2_256', CAST(? AS NVARCHAR(MAX))), ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole().name());
            stmt.executeUpdate();
        }
    }

    public UserTelecom findById(int id) throws Exception {
        String sql = "SELECT * FROM UserTelecom WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public UserTelecom findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM UserTelecom WHERE email = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public ArrayList<UserTelecom> getAll() throws Exception {
        ArrayList<UserTelecom> list = new ArrayList<>();
        String sql = "SELECT * FROM UserTelecom";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public void update(UserTelecom user) throws Exception {
        String sql = "UPDATE UserTelecom SET username=?, email=?, role=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole().name());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM UserTelecom WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public UserTelecom login(String email, String password) throws Exception {
        String sql = "SELECT * FROM UserTelecom "
                   + "WHERE email = ? "
                   + "AND password = HASHBYTES('SHA2_256', CAST(? AS NVARCHAR(MAX)))";

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