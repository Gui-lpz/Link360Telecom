package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.MobileLine;
import model.entities.LineType;
import model.entities.Technology;
import model.entities.LineStatus;
import model.entities.SimType;

public class MobileLineData {

    private MobileLine map(ResultSet rs) throws SQLException {
        return new MobileLine(
                rs.getInt("id"),
                rs.getString("phone_number"),
                LineType.valueOf(rs.getString("line_type")),
                Technology.valueOf(rs.getString("technology")),
                rs.getDate("activation_date").toLocalDate(),
                LineStatus.valueOf(rs.getString("status")),
                SimType.valueOf(rs.getString("sim_type")),
                rs.getInt("client_id")
        );
    }

    public void add(MobileLine line) throws Exception {
        String sql = "INSERT INTO MobileLine "
                + "(phone_number, line_type, technology, activation_date, status, sim_type, client_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, line.getPhoneNumber());
            stmt.setString(2, line.getLineType().name());
            stmt.setString(3, line.getTechnology().name());
            stmt.setDate(4, Date.valueOf(line.getActivationDate()));
            stmt.setString(5, line.getStatus().name());
            stmt.setString(6, line.getSimType().name());
            stmt.setInt(7, line.getClientId());

            stmt.executeUpdate();
        }
    }

    public MobileLine findById(int id) throws Exception {
        String sql = "SELECT * FROM MobileLine WHERE id = ?";

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

    public ArrayList<MobileLine> getAll() throws Exception {
        ArrayList<MobileLine> list = new ArrayList<>();
        String sql = "SELECT * FROM MobileLine";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    public ArrayList<MobileLine> findByClientId(int clientId) throws Exception {
        ArrayList<MobileLine> list = new ArrayList<>();
        String sql = "SELECT * FROM MobileLine WHERE client_id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }

        return list;
    }

    public void update(MobileLine line) throws Exception {
        String sql = "UPDATE MobileLine SET phone_number=?, line_type=?, technology=?, "
                + "activation_date=?, status=?, sim_type=?, client_id=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, line.getPhoneNumber());
            stmt.setString(2, line.getLineType().name());
            stmt.setString(3, line.getTechnology().name());
            stmt.setDate(4, Date.valueOf(line.getActivationDate()));
            stmt.setString(5, line.getStatus().name());
            stmt.setString(6, line.getSimType().name());
            stmt.setInt(7, line.getClientId());
            stmt.setInt(8, line.getId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM MobileLine WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}