package com.foodapp.main;

import com.food.dao.OrderHistoryDAO;
import com.food.dao.Impl.OrderHistoryDAOImpl;
import com.food.model.OrderHistory;

import java.util.List;
import java.util.Scanner;

public class LaunchOrderHistory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
        
        while (true) {
            System.out.println("\n--- Order History Operations ---");
            System.out.println("1. Add Order History");
            System.out.println("2. View All Order Histories");
            System.out.println("3. View Order History by ID");
            System.out.println("4. Update Order History Status");
            System.out.println("5. Delete Order History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline character
            
            switch (choice) {
                case 1:
                    // Add Order History
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Enter Item TotalAmount: ");
                    int itemTotal = sc.nextInt();
                    sc.nextLine(); // consume newline character
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();
                    
                    OrderHistory newOrderHistory = new OrderHistory(0, orderId, userId, itemTotal, status);
                    orderHistoryDAO.insert(newOrderHistory);
                    System.out.println("Order History added successfully!");
                    break;
                
                case 2:
                    // View All Order Histories
                    List<OrderHistory> orderHistories = orderHistoryDAO.fetchAll();
                    if (orderHistories.isEmpty()) {
                        System.out.println("No order histories found.");
                    } else {
                        System.out.println("--- Order Histories ---");
                        for (OrderHistory oh : orderHistories) {
                            System.out.println(oh);
                        }
                    }
                    break;
                
                case 3:
                    // View Order History by ID
                    System.out.print("Enter Order History ID: ");
                    int orderHistoryId = sc.nextInt();
                    OrderHistory orderHistory = orderHistoryDAO.fetchOne(orderHistoryId);
                    if (orderHistory != null) {
                        System.out.println(orderHistory);
                    } else {
                        System.out.println("Order History not found.");
                    }
                    break;
                
                case 4:
                	// Update Order History
                    System.out.print("Enter Order History ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // consume newline character
                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();
                    System.out.print("Enter New Item Total: ");
                    int newItemTotal = sc.nextInt();
                    orderHistoryDAO.update(updateId, newItemTotal, newStatus);
                    System.out.println("Order History updated successfully!");
                    break;
                
                case 5:
                    // Delete Order History
                    System.out.print("Enter Order History ID: ");
                    int deleteId = sc.nextInt();
                    orderHistoryDAO.delete(deleteId);
                    System.out.println("Order History deleted successfully!");
                    break;
                
                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

