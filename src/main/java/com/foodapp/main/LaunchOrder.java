package com.foodapp.main;

import java.util.List;
import java.util.Scanner;
import com.food.dao.OrderDAO;
import com.food.dao.Impl.OrderDAOImpl;
import com.food.model.Order;

public class LaunchOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderDAO orderDAO = new OrderDAOImpl();

        while (true) {
            System.out.println("\n======= ORDER MANAGEMENT =======");
            System.out.println("1. Add Order");
            System.out.println("2. View All Orders");
            System.out.println("3. View Order by ID");
            System.out.println("4. Update Order Status");
            System.out.println("5. Delete Order");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Order
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Enter Restaurant ID: ");
                    int restaurantId = sc.nextInt();
                    System.out.print("Enter Total Amount: ");
                    int totalAmount = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();
                    System.out.print("Enter Payment Mode: ");
                    String paymentMode = sc.nextLine();

                    Order newOrder = new Order(orderId, userId, restaurantId, totalAmount, status, paymentMode);
                    orderDAO.insert(newOrder);
                    System.out.println("Order added successfully!");
                    break;

                case 2:
                    // View All Orders
                    List<Order> orders = orderDAO.fetchAll();
                    System.out.println("==== List of Orders ====");
                    for (Order order : orders) {
                        System.out.println(order);
                    }
                    break;

                case 3:
                    // View Order by ID
                    System.out.print("Enter Order ID to fetch: ");
                    int fetchOrderId = sc.nextInt();
                    Order order = orderDAO.fetchOne(fetchOrderId);
                    if (order != null) {
                        System.out.println("Order Details: " + order);
                    } else {
                        System.out.println("Order not found with ID: " + fetchOrderId);
                    }
                    break;

                case 4:
                    // Update Order Status
                    System.out.print("Enter Order ID to update: ");
                    int updateOrderId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();

                    orderDAO.update(updateOrderId, newStatus);
                    System.out.println("Order status updated successfully!");
                    break;

                case 5:
                    // Delete Order
                    System.out.print("Enter Order ID to delete: ");
                    int deleteOrderId = sc.nextInt();
                    orderDAO.delete(deleteOrderId);
                    System.out.println("Order deleted successfully!");
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting Order Management System. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

