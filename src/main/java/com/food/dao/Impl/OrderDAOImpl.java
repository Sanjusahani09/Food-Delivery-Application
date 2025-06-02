package com.food.dao.Impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.food.dao.OrderDAO;
import com.food.model.Order;

import jakarta.servlet.http.HttpSession;

public class OrderDAOImpl implements OrderDAO {

	
		
	
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapplication", "root", "Dharam@123");
    }

    @Override
    public void insert(Order order) {
        String query = "INSERT INTO Orders (userId, restaurantId, total_amount, status, paymentMode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS)) {
            //ps.setInt(1, order.getOrderId());
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getRestaurantId());
            ps.setDouble(3, order.getTotal_amount());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getPaymentMode());
            ps.executeUpdate();
            try(ResultSet genratedKeys=ps.getGeneratedKeys()) {
            	if(genratedKeys.next()) {
            		order.setOrderId(genratedKeys.getInt(1));
            	}
            }
            catch(Exception e) {
            	
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> fetchAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("restaurantId"),
                    rs.getInt("total_amount"),
                    rs.getString("status"),
                    rs.getString("paymentMode"),
                    rs.getDate("orderDate")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    
    @Override
    public List<Order> fetchAllUsingUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders where userId=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){;
        		stmt.setInt(1, userId);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("restaurantId"),
                    rs.getInt("total_amount"),
                    rs.getString("status"),
                    rs.getString("paymentMode"),
                    rs.getDate("orderDate")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order fetchOne(int orderId) {
        String query = "SELECT * FROM Orders WHERE orderId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                    rs.getInt("orderId"),
                    rs.getInt("userId"),
                    rs.getInt("restaurantId"),
                    rs.getInt("total_amount"),
                    rs.getString("status"),
                    rs.getString("paymentMode"),
                    rs.getDate("orderDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int orderId, String status) {
        String query = "UPDATE Orders SET status = ? WHERE orderId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int orderId) {
        String query = "DELETE FROM Orders WHERE orderId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

