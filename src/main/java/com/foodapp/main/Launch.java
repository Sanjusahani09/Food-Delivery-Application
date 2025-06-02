package com.foodapp.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.food.dao.UserDAO;
import com.food.dao.Impl.UserDAOImpl;
import com.food.model.User;

public class Launch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== User Management System =====");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. View User by ID");
            System.out.println("4. Update User Password");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Add User
//                    System.out.println("Enter User ID:");
//                    int userId = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter Username:");
                    String username = scanner.nextLine();

                    System.out.println("Enter Password:");
                    String password = scanner.nextLine();

                    System.out.println("Enter Email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter Address:");
                    String address = scanner.nextLine();

                    User user = new User(username, password, email, address);
                    userDAO.insert(user);
                    System.out.println("User added successfully!");
                    break;

                case 2:
                    // View All Users
                    ArrayList<User> users = userDAO.fetchAll();
                    System.out.println("===== List of Users =====");
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;

                case 3:
                    // View User by ID
                    System.out.println("Enter User ID to fetch:");
                    int fetchId = scanner.nextInt();
                    User fetchedUser = userDAO.fetchOne(fetchId);
                    if (fetchedUser != null) {
                        System.out.println("User Details: " + fetchedUser);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;

                case 4:
                    // Update User Password
                    System.out.println("Enter User ID:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter New Password:");
                    String newPassword = scanner.nextLine();

                    userDAO.update(updateId, newPassword);
                    System.out.println("Password updated successfully!");
                    break;

                case 5:
                    // Delete User
                    System.out.println("Enter User ID to delete:");
                    int deleteId = scanner.nextInt();
                    userDAO.delete(deleteId);
                    System.out.println("User deleted successfully!");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting the application...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
