package com.food.dao;



import com.food.model.OrderHistory;
import java.util.List;

public interface OrderHistoryDAO {
    void insert(OrderHistory orderHistory);
    List<OrderHistory> fetchAll();
    OrderHistory fetchOne(int orderHistoryId);
    void update(int orderHistoryId, int itemTotal, String status);
    void delete(int orderHistoryId);
}
