package com.food.dao.Impl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Dharam@123";

    // Helper method to establish a database connection
    private Connection getConnection() throws SQLException {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    

    @Override
    public void insert(Restaurant user) {
        String query = "INSERT INTO restaurant (restaurantId, name, cuisineType, deliveryTime, address, ratings, isActive, ImagePath) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, user.getRestaurantId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getCuisineTime());
            ps.setInt(4, user.getDeliveryTime());
            ps.setString(5, user.getAddress());
            ps.setFloat(6, user.getRatings());
            ps.setBoolean(7, user.isActive());
            ps.setString(8, user.getImagePath());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> fetchAll() {
        List<Restaurant> users = new ArrayList<>();
        String query = "SELECT * FROM restaurant";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
            	Restaurant user = new Restaurant(
                    rs.getInt("restaurantId"),
                    rs.getString("name"),
                    rs.getString("cuisineType"),
                    rs.getInt("deliveryTime"),
                    rs.getString("address"),
                    rs.getFloat("ratings"),
                    rs.getBoolean("isActive"),
                    rs.getString("ImagePath")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Restaurant fetchOne(int userId) {
        String query = "SELECT * FROM restaurant WHERE restaurantId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Restaurant(
                        rs.getInt("restaurantId"),
                        rs.getString("name"),
                        rs.getString("cuisineType"),
                        rs.getInt("deliveryTime"),
                        rs.getString("address"),
                        rs.getFloat("ratings"),
                        rs.getBoolean("isActive"),
                        rs.getString("ImagePath")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int restaurantId, String newAddress, float newRatings) {
        String query = "UPDATE restaurant SET address = ?, ratings = ? WHERE restaurantId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newAddress);
            ps.setFloat(2, newRatings);
            ps.setInt(3, restaurantId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int restaurantId) {
        String query = "DELETE FROM restaurants WHERE restaurantId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, restaurantId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Restaurant> searchRestaurants(String query) {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants WHERE (name LIKE ? OR cuisine LIKE ?)";
        try (Connection conn=getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurantId"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineTime(rs.getString("cuisineType"));
                restaurant.setDeliveryTime(rs.getInt("deliverytime"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRatings(rs.getFloat("ratings"));
                restaurant.setActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("ImagePath"));
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
