package com.food.dao;



import java.util.List;
import com.food.model.OrderItem;

public interface OrderItemDAO {
    void insert(OrderItem orderItem); // Add a new order item

    List<OrderItem> fetchAll(); // Fetch all order items

    OrderItem fetchOne(int orderItemId); // Fetch a single order item by ID

    void update(int orderItemId, int quantity, int itemTotal); // Update the quantity and item total for an order item

    void delete(int orderItemId); // Delete an order item by ID
}
