package com.food.dao;

import java.util.List;
import com.food.model.Order;

public interface OrderDAO {
    void insert(Order order);                 
    List<Order> fetchAll();  
    List<Order>fetchAllUsingUserId(int userId);
    Order fetchOne(int orderId);              
    void update(int orderId, String status);  
    void delete(int orderId);                 
}

