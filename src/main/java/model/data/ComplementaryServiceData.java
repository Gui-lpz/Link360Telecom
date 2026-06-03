package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.ComplementaryService;

public class ComplementaryServiceData {

    private ComplementaryService map(ResultSet rs) throws SQLException {
        return new ComplementaryService(
                rs.getInt("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("monthly_cost"),
                rs.getString("category")
        );
    }

    public void add(ComplementaryService service) throws Exception {
        String sql = "INSERT INTO ComplementaryService "
                + "(code, name, description, monthly_cost, category) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, service.getCode());
            stmt.setString(2, service.getName());
            stmt.setString(3, service.getDescription());
            stmt.setBigDecimal(4, service.getMonthlyCost());
            stmt.setString(5, service.getCategory());

            stmt.executeUpdate();
        }
    }

    public ComplementaryService findById(int id) throws Exception {
        String sql = "SELECT * FROM ComplementaryService WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }

        return null;
    }

    public ArrayList<ComplementaryService> getAll() throws Exception {
        ArrayList<ComplementaryService> list = new ArrayList<>();
        String sql = "SELECT * FROM ComplementaryService";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    public void update(ComplementaryService service) throws Exception {
        String sql = "UPDATE ComplementaryService SET code=?, name=?, description=?, "
                + "monthly_cost=?, category=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, service.getCode());
            stmt.setString(2, service.getName());
            stmt.setString(3, service.getDescription());
            stmt.setBigDecimal(4, service.getMonthlyCost());
            stmt.setString(5, service.getCategory());
            stmt.setInt(6, service.getId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM ComplementaryService WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}