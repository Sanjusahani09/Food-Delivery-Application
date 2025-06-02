package com.food.dao.Impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;

public class MenuDAOImpl implements MenuDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Dharam@123";

    // Helper method to establish a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void insert(Menu menu) {
        String query = "INSERT INTO menu (menuId, restaurantId, name, description, price, isAvailable, imagePath) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, menu.getMenuId());
            ps.setInt(2, menu.getRestaurantId());
            ps.setString(3, menu.getName());
            ps.setString(4, menu.getDescription());
            ps.setInt(5, menu.getPrice());
            ps.setBoolean(6, menu.isAvailable());
            ps.setString(7, menu.getImagePath());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> fetchAll() {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restaurantId"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imagePath")
                );
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
    
    @Override
    public List<Menu> fetchMenuByRestaurantId(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu where restaurantId=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){
        	 stmt.setInt(1,restaurantId);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("menuId"),
                    rs.getInt("restaurantId"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("price"),
                    rs.getBoolean("isAvailable"),
                    rs.getString("imagePath")
                );
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public Menu fetchOne(int menuId) {
        String query = "SELECT * FROM menu WHERE menuId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, menuId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getBoolean("isAvailable"),
                        rs.getString("imagePath")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    @Override
    public void update(int menuId, String newDescription, int newPrice, boolean isAvailable) {
        String query = "UPDATE menu SET description = ?, price = ?, isAvailable = ? WHERE menuId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newDescription);
            ps.setInt(2, newPrice);
            ps.setBoolean(3, isAvailable);
            ps.setInt(4, menuId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int menuId) {
        String query = "DELETE FROM menu WHERE menuId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, menuId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
