package com.foodapp.main;

import java.util.List;
import java.util.Scanner;

import com.food.dao.MenuDAO;
import com.food.dao.Impl.MenuDAOImpl;
import com.food.model.Menu;

public class LaunchMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuDAO menuDAO = new MenuDAOImpl();

        while (true) {
            System.out.println("\n======= MENU MANAGEMENT =======");
            System.out.println("1. Add Menu");
            System.out.println("2. View All Menus");
            System.out.println("3. View Menu by ID");
            System.out.println("4. Update Menu");
            System.out.println("5. Delete Menu");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Menu
                    System.out.print("Enter Menu ID: ");
                    int menuId = sc.nextInt();
                    System.out.print("Enter Restaurant ID: ");
                    int restaurantId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Menu Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Price: ");
                    int price = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = sc.nextBoolean();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Image Path: ");
                    String imagePath = sc.nextLine();

                    Menu newMenu = new Menu(menuId, restaurantId, name, description, price, isAvailable, imagePath);
                    menuDAO.insert(newMenu);
                    System.out.println("Menu added successfully!");
                    break;

                case 2:
                    // View All Menus
                    List<Menu> menus = menuDAO.fetchAll();
                    System.out.println("==== List of Menus ====");
                    for (Menu menu : menus) {
                        System.out.println(menu);
                    }
                    break;

                case 3:
                    // View Menu by ID
                    System.out.print("Enter Menu ID to fetch: ");
                    int fetchMenuId = sc.nextInt();
                    Menu menu = menuDAO.fetchOne(fetchMenuId);
                    if (menu != null) {
                        System.out.println("Menu Details: " + menu);
                    } else {
                        System.out.println("Menu not found with ID: " + fetchMenuId);
                    }
                    break;

                case 4:
                    // Update Menu
                    System.out.print("Enter Menu ID to update: ");
                    int updateMenuId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter New Description: ");
                    String newDescription = sc.nextLine();
                    System.out.print("Enter New Price: ");
                    int newPrice = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    boolean newAvailability = sc.nextBoolean();

                    menuDAO.update(updateMenuId, newDescription, newPrice, newAvailability);
                    System.out.println("Menu updated successfully!");
                    break;

                case 5:
                    // Delete Menu
                    System.out.print("Enter Menu ID to delete: ");
                    int deleteMenuId = sc.nextInt();
                    menuDAO.delete(deleteMenuId);
                    System.out.println("Menu deleted successfully!");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting Menu Management System. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
