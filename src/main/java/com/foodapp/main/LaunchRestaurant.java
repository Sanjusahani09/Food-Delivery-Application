package com.foodapp.main;

import java.util.List;
import java.util.Scanner;

import com.food.dao.RestaurantDAO;
import com.food.dao.Impl.RestaurantDAOImpl;
import com.food.model.Restaurant; // Assuming User represents the Restaurant entity

public class LaunchRestaurant {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Restaurant Management System =====");
            System.out.println("1. Add Restaurant");
            System.out.println("2. View All Restaurants");
            System.out.println("3. View Restaurant by ID");
            System.out.println("4. Update Restaurant");
            System.out.println("5. Delete Restaurant");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add a new restaurant
                    System.out.println("Enter Restaurant ID:");
                    int restaurantId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter Restaurant Name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter Cuisine Type:");
                    String cuisineType = scanner.nextLine();

                    System.out.println("Enter Delivery Time (in minutes):");
                    int deliveryTime = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter Address:");
                    String address = scanner.nextLine();

                    System.out.println("Enter Ratings:");
                    float ratings = scanner.nextFloat();

                    System.out.println("Is the restaurant active? (true/false):");
                    boolean isActive = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter Image Path:");
                    String ImagePath = scanner.nextLine();

                    Restaurant res = new Restaurant(restaurantId, name, cuisineType, deliveryTime, address, ratings, isActive, ImagePath);
                    restaurantDAO.insert(res);
                    System.out.println("Restaurant added successfully!");
                    break;

                case 2:
                    // View all restaurants
                    List<Restaurant> restaurants = restaurantDAO.fetchAll();
                    System.out.println("===== List of Restaurants =====");
                    for (Restaurant r : restaurants) {
                        System.out.println(r);
                    }
                    break;

                case 3:
                    // View a restaurant by ID
                    System.out.println("Enter Restaurant ID:");
                    int fetchId = scanner.nextInt();
                    Restaurant fetchedRestaurant = restaurantDAO.fetchOne(fetchId);
                    if (fetchedRestaurant != null) {
                        System.out.println("Restaurant Details: " + fetchedRestaurant);
                    } else {
                        System.out.println("Restaurant not found!");
                    }
                    break;

                case 4:
                    // Update a restaurant's address and ratings
                    System.out.println("Enter Restaurant ID to update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("Enter New Address:");
                    String newAddress = scanner.nextLine();

                    System.out.println("Enter New Ratings:");
                    float newRatings = scanner.nextFloat();

                    restaurantDAO.update(updateId, newAddress, newRatings);
                    System.out.println("Restaurant updated successfully!");
                    break;

                case 5:
                    // Delete a restaurant
                    System.out.println("Enter Restaurant ID to delete:");
                    int deleteId = scanner.nextInt();
                    restaurantDAO.delete(deleteId);
                    System.out.println("Restaurant deleted successfully!");
                    break;

                case 6:
                    // Exit the application
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

