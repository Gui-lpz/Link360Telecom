package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.Plan;

public class PlanServiceData {

    private Plan map(ResultSet rs) throws SQLException {
        return new Plan(
                rs.getInt("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBigDecimal("monthly_fee"),
                rs.getDouble("included_gb"),
                rs.getInt("included_minutes"),
                rs.getInt("included_messages"),
                rs.getBigDecimal("excess_cost"),
                rs.getInt("commercial_category_id")
        );
    }

    public void add(Plan plan) throws Exception {
        String sql = "INSERT INTO PlanService "
                + "(code, name, description, monthly_fee, included_gb, included_minutes, "
                + "included_messages, excess_cost, commercial_category_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plan.getCode());
            stmt.setString(2, plan.getName());
            stmt.setString(3, plan.getDescription());
            stmt.setBigDecimal(4, plan.getMonthlyFee());
            stmt.setDouble(5, plan.getIncludedGB());
            stmt.setInt(6, plan.getIncludedMinutes());
            stmt.setInt(7, plan.getIncludedMessages());
            stmt.setBigDecimal(8, plan.getExcessCost());
            stmt.setInt(9, plan.getCommercialCategoryId());

            stmt.executeUpdate();
        }
    }

    public Plan findById(int id) throws Exception {
        String sql = "SELECT * FROM PlanService WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }

        return null;
    }

    public ArrayList<Plan> getAll() throws Exception {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM PlanService";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    public ArrayList<Plan> getAllWithCategory() throws Exception {
        ArrayList<Plan> list = new ArrayList<>();
        String sql = "SELECT p.id, p.code, p.name, p.description, "
                + "       p.monthly_fee, p.included_gb, p.included_minutes, "
                + "       p.included_messages, p.excess_cost, p.commercial_category_id, "
                + "       c.description AS category_description, "
                + "       c.max_connection_speed "
                + "FROM PlanService p "
                + "INNER JOIN CommercialCategory c ON p.commercial_category_id = c.id";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Plan plan = map(rs);
                plan.setCategoryDescription(rs.getString("category_description"));
                plan.setMaxConnectionSpeed(rs.getString("max_connection_speed"));
                list.add(plan);
            }
        }
        return list;
    }

    public void update(Plan plan) throws Exception {
        String sql = "UPDATE PlanService SET code=?, name=?, description=?, monthly_fee=?, "
                + "included_gb=?, included_minutes=?, included_messages=?, excess_cost=?, "
                + "commercial_category_id=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plan.getCode());
            stmt.setString(2, plan.getName());
            stmt.setString(3, plan.getDescription());
            stmt.setBigDecimal(4, plan.getMonthlyFee());
            stmt.setDouble(5, plan.getIncludedGB());
            stmt.setInt(6, plan.getIncludedMinutes());
            stmt.setInt(7, plan.getIncludedMessages());
            stmt.setBigDecimal(8, plan.getExcessCost());
            stmt.setInt(9, plan.getCommercialCategoryId());
            stmt.setInt(10, plan.getId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM PlanService WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
