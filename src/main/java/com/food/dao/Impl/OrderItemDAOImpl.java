package com.food.dao.Impl;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Dharam@123";

    @Override
    public void insert(OrderItem orderItem) {
        String query = "INSERT INTO OrderItem (orderId, menuId, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getItemTotal());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem fetchOne(int orderItemId) {
        OrderItem orderItem = null;
        String query = "SELECT * FROM OrderItem WHERE orderItemId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderItemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderItem = new OrderItem();
                    orderItem.setOrderItemId(rs.getInt("orderItemId"));
                    orderItem.setOrderId(rs.getInt("orderId"));
                    orderItem.setMenuId(rs.getInt("menuId"));
                    orderItem.setQuantity(rs.getInt("quantity"));
                    orderItem.setItemTotal(rs.getInt("itemTotal"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> fetchAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM OrderItem";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(rs.getInt("orderItemId"));
                orderItem.setOrderId(rs.getInt("orderId"));
                orderItem.setMenuId(rs.getInt("menuId"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setItemTotal(rs.getInt("itemTotal"));
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public void update(int orderItemId, int quantity, int itemTotal) {
        String query = "UPDATE OrderItem SET quantity = ?, itemTotal = ? WHERE orderItemId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, quantity);
            ps.setInt(2, itemTotal);
            ps.setInt(3, orderItemId);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("OrderItem updated successfully!");
            } else {
                System.out.println("No OrderItem found with ID " + orderItemId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int orderItemId) {
        String query = "DELETE FROM OrderItem WHERE orderItemId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, orderItemId);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("OrderItem deleted successfully!");
            } else {
                System.out.println("No OrderItem found with ID " + orderItemId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
