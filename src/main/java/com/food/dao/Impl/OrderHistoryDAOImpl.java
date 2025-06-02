package com.food.dao.Impl;



import com.food.dao.OrderHistoryDAO;
import com.food.model.OrderHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Dharam@123";

    @Override
    public void insert(OrderHistory orderHistory) {
        String query = "INSERT INTO order_history (orderHistoryId, orderId, userId, itemTotal, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistory.getOrderHistoryId());
            ps.setInt(2, orderHistory.getOrderId());
            ps.setInt(3, orderHistory.getUserId());
            ps.setInt(4, orderHistory.getItemTotal());
            ps.setString(5, orderHistory.getStatus());
            ps.executeUpdate();
            System.out.println("Order History added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderHistory> fetchAll() {
        String query = "SELECT * FROM order_history";
        List<OrderHistory> orderHistories = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                orderHistory.setOrderId(rs.getInt("orderId"));
                orderHistory.setUserId(rs.getInt("userId"));
                orderHistory.setItemTotal(rs.getInt("itemTotal"));
                orderHistory.setStatus(rs.getString("status"));
                orderHistories.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistories;
    }

    @Override
    public OrderHistory fetchOne(int orderHistoryId) {
        String query = "SELECT * FROM order_history WHERE orderHistoryId = ?";
        OrderHistory orderHistory = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderHistory = new OrderHistory();
                    orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                    orderHistory.setOrderId(rs.getInt("orderId"));
                    orderHistory.setUserId(rs.getInt("userId"));
                    orderHistory.setItemTotal(rs.getInt("itemTotal"));
                    orderHistory.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public void update(int orderHistoryId, int itemTotal, String status) {
        String query = "UPDATE order_history SET itemTotal = ?, status = ? WHERE orderHistoryId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, itemTotal);
            ps.setString(2, status);
            ps.setInt(3, orderHistoryId);
            ps.executeUpdate();
            System.out.println("Order History updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int orderHistoryId) {
        String query = "DELETE FROM order_history WHERE orderHistoryId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderHistoryId);
            ps.executeUpdate();
            System.out.println("Order History deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

