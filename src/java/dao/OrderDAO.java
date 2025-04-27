package dao;

import model.Payment;
import model.PaymentMethod;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;

public class OrderDAO {
    private Connection getConnection() throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/glowydays", "nbuser", "nbuser");
    }

    public void saveOrder(String orderId, String userId, String orderDate, Double subtotal,
                          Double taxAmount, Double deliveryFee, Double totalAmount,
                          List<CartItem> cartItems, Integer paymentId, Integer shippingId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();

            // 1. Generate format for orderId
            String nextOrderId = generateOrderId(con);
            
            // 2. Insert into ORDERS table
            String orderSql = "INSERT INTO NBUSER.ORDERS " +
                    "(\"orderId\", \"userId\", \"orderDate\", \"paymentId\", \"shippingId\", \"subtotal\", \"taxAmount\", \"deliveryFee\", \"totalAmount\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(orderSql);
            pstmt.setString(1, orderId);
            orderStmt.setInt(2, Integer.parseInt(userId));
            pstmt.setString(3, orderDate);
            pstmt.setInt(4, paymentId);
            pstmt.setInt(5, shippingId);
            pstmt.setDouble(6, subtotal);
            pstmt.setDouble(7, taxAmount);
            pstmt.setDouble(8, deliveryFee);
            pstmt.setDouble(9, totalAmount);
            pstmt.executeUpdate();

            // 3. Insert into ORDERITEMS table
            String itemSql = "INSERT INTO NBUSER.ORDERITEMS " +
                    "(\"orderId\", \"productId\", \"productName\", \"quantity\", \"unitPrice\", \"subtotal\") " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(itemSql);
            for (CartItem item : cartItems) {
                pstmt.setString(1, orderId);
                pstmt.setInt(2, item.getProduct().getId());
                pstmt.setString(3, item.getProduct().getName());
                pstmt.setInt(4, item.getQuantity());
                pstmt.setDouble(5, item.getProduct().getPrice());
                pstmt.setDouble(6, item.getSubtotal());
                pstmt.executeUpdate();
            }

        } catch (Exception e) {
            // Rollback transaction on error
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
    
    private String generateOrderId(Connection con) throws SQLException {
        String sql = "SELECT MAX(\"orderId\") AS maxId FROM NBUSER.ORDERS";

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next() && rs.getString("maxId") != null) {
                String maxId = rs.getString("maxId"); // Eg: ORD-0005
                int num = Integer.parseInt(maxId.substring(4)); // Get 5
                return String.format("ORD-%04d", num + 1); // ORD-0006
            } else {
                return "ORD-0001"; // First order
            }
        }
    }

}
