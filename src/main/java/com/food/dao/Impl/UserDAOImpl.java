package com.food.dao.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.food.dao.UserDAO;
import com.food.model.User;
import com.food.secure.SecureData;

public class UserDAOImpl implements UserDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
	private static final String USER = "root";
	private static final String PASSWORD = "Dharam@123";

	
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
	public int insert(User user) {
		String query = "INSERT INTO user (username, password, email, address) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			//ps.setInt(1, user.getUserid());
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getAddress());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<User> fetchAll() {
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT * FROM user";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				User user = new User(
						//rs.getInt("userid"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("address")
						);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User fetchOne(int userId) {
		String query = "SELECT * FROM user WHERE userid = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new User(
							//rs.getInt("userid"),
							rs.getString("username"),
							rs.getString("password"),
							rs.getString("email"),
							rs.getString("address")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public User fetchEmailPassword(String email,String password) {
		User user=null;
		try (Connection conn = getConnection()){
			String query="select * from user where email=? and password=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet res=pstmt.executeQuery();
			if(res.next()) {
				user=new User(
						res.getInt("userid"),
						SecureData.decrypt(res.getString("username")),
								SecureData.decrypt(res.getString("password")),
										SecureData.decrypt(res.getString("email")),
												SecureData.decrypt(res.getString("address")));


			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public User fetchEmail(String email) {
		User user=null;
		try (Connection conn = getConnection()){
			String query="select * from user where email=?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, email);
			ResultSet res=pstmt.executeQuery();
			if(res.next()) {
				user=new User();
				user.setEmail(res.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(int userId, String newPassword) {
		String query = "UPDATE user SET password = ? WHERE userid = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, newPassword);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updatePassword(String email, String newPassword) {
		String query = "UPDATE user SET password = ? WHERE email = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, newPassword);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int userId) {
		String query = "DELETE FROM user WHERE userid = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

