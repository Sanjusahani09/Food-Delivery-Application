package com.foodapp.main;

import com.food.dao.OrderItemDAO;
import com.food.dao.Impl.OrderItemDAOImpl;
import com.food.model.OrderItem;

import java.util.List;
import java.util.Scanner;

public class LaunchOrderItem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        while (true) {
            System.out.println("\n=== Order Item Management ===");
            System.out.println("1. Add Order Item");
            System.out.println("2. Fetch All Order Items");
            System.out.println("3. Fetch One Order Item");
            System.out.println("4. Update Order Item");
            System.out.println("5. Delete Order Item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Order Item
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter Menu ID: ");
                    int menuId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Item Total: ");
                    int itemTotal = scanner.nextInt();

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(menuId);
                    orderItem.setQuantity(quantity);
                    orderItem.setItemTotal(itemTotal);

                    orderItemDAO.insert(orderItem);
                    System.out.println("Order Item added successfully!");
                    break;

                case 2: // Fetch All Order Items
                    List<OrderItem> orderItems = orderItemDAO.fetchAll();
                    System.out.println("\n--- Order Items ---");
                    for (OrderItem item : orderItems) {
                        System.out.println(item);
                    }
                    break;

                case 3: // Fetch One Order Item
                    System.out.print("Enter Order Item ID: ");
                    int fetchOrderItemId = scanner.nextInt();
                    OrderItem fetchedOrderItem = orderItemDAO.fetchOne(fetchOrderItemId);
                    if (fetchedOrderItem != null) {
                        System.out.println("\n--- Order Item Details ---");
                        System.out.println(fetchedOrderItem);
                    } else {
                        System.out.println("Order Item not found!");
                    }
                    break;

                case 4: // Update Order Item
                    System.out.print("Enter Order Item ID to update: ");
                    int updateOrderItemId = scanner.nextInt();
                    System.out.print("Enter New Quantity: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Enter New Item Total: ");
                    int newItemTotal = scanner.nextInt();

                    orderItemDAO.update(updateOrderItemId, newQuantity, newItemTotal);
                    break;

                case 5: // Delete Order Item
                    System.out.print("Enter Order Item ID to delete: ");
                    int deleteOrderItemId = scanner.nextInt();
                    orderItemDAO.delete(deleteOrderItemId);
                    break;

                case 6: // Exit
                    System.out.println("Exiting Order Item Management. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
