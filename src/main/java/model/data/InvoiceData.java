package model.data;

import java.sql.*;
import java.util.ArrayList;
import model.entities.Invoice;
import model.entities.PaymentStatus;

public class InvoiceData {

    private Invoice map(ResultSet rs) throws SQLException {
        Date paymentDate = rs.getDate("payment_date");

        return new Invoice(
                rs.getInt("id"),
                rs.getString("invoice_number"),
                rs.getInt("client_id"),
                rs.getInt("line_id"),
                rs.getDate("invoice_date").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                rs.getBigDecimal("amount"),
                rs.getBigDecimal("taxes"),
                rs.getBigDecimal("applied_discounts"),
                rs.getInt("redeemed_points"),
                rs.getBigDecimal("final_amount"),
                paymentDate != null ? paymentDate.toLocalDate() : null,
                PaymentStatus.valueOf(rs.getString("payment_status"))
        );
    }

    public void add(Invoice invoice) throws Exception {
        String sql = "INSERT INTO Invoice "
                + "(invoice_number, client_id, line_id, invoice_date, due_date, amount, taxes, "
                + "applied_discounts, redeemed_points, final_amount, payment_date, payment_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, invoice.getInvoiceNumber());
            stmt.setInt(2, invoice.getClientId());
            stmt.setInt(3, invoice.getLineId());
            stmt.setDate(4, Date.valueOf(invoice.getInvoiceDate()));
            stmt.setDate(5, Date.valueOf(invoice.getDueDate()));
            stmt.setBigDecimal(6, invoice.getAmount());
            stmt.setBigDecimal(7, invoice.getTaxes());
            stmt.setBigDecimal(8, invoice.getAppliedDiscounts());
            stmt.setInt(9, invoice.getRedeemedPoints());
            stmt.setBigDecimal(10, invoice.getFinalAmount());

            if (invoice.getPaymentDate() != null) {
                stmt.setDate(11, Date.valueOf(invoice.getPaymentDate()));
            } else {
                stmt.setNull(11, Types.DATE);
            }

            stmt.setString(12, invoice.getPaymentStatus().name());

            stmt.executeUpdate();
        }
    }

    public Invoice findById(int id) throws Exception {
        String sql = "SELECT * FROM Invoice WHERE id = ?";

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

    public ArrayList<Invoice> getAll() throws Exception {
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    public ArrayList<Invoice> findByClientId(int clientId) throws Exception {
        ArrayList<Invoice> list = new ArrayList<>();
        String sql = "SELECT * FROM Invoice WHERE client_id = ?";

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

    public void update(Invoice invoice) throws Exception {
        String sql = "UPDATE Invoice SET invoice_number=?, client_id=?, line_id=?, invoice_date=?, "
                + "due_date=?, amount=?, taxes=?, applied_discounts=?, redeemed_points=?, "
                + "final_amount=?, payment_date=?, payment_status=? WHERE id=?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, invoice.getInvoiceNumber());
            stmt.setInt(2, invoice.getClientId());
            stmt.setInt(3, invoice.getLineId());
            stmt.setDate(4, Date.valueOf(invoice.getInvoiceDate()));
            stmt.setDate(5, Date.valueOf(invoice.getDueDate()));
            stmt.setBigDecimal(6, invoice.getAmount());
            stmt.setBigDecimal(7, invoice.getTaxes());
            stmt.setBigDecimal(8, invoice.getAppliedDiscounts());
            stmt.setInt(9, invoice.getRedeemedPoints());
            stmt.setBigDecimal(10, invoice.getFinalAmount());

            if (invoice.getPaymentDate() != null) {
                stmt.setDate(11, Date.valueOf(invoice.getPaymentDate()));
            } else {
                stmt.setNull(11, Types.DATE);
            }

            stmt.setString(12, invoice.getPaymentStatus().name());
            stmt.setInt(13, invoice.getId());

            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Invoice WHERE id = ?";

        try (Connection conn = DbConnection_Link360Telecom.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}