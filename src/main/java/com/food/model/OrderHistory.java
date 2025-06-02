package com.food.model;



public class OrderHistory {
    private int orderHistoryId;
    private int orderId;
    private int userId;
    private int itemTotal;
    private String status;

    // Constructors
    public OrderHistory() {}

    public OrderHistory(int orderHistoryId, int orderId, int userId, int itemTotal, String status) {
        this.orderHistoryId = orderHistoryId;
        this.orderId = orderId;
        this.userId = userId;
        this.itemTotal = itemTotal;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString Method
    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderHistoryId=" + orderHistoryId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", itemTotal=" + itemTotal +
                ", status='" + status + '\'' +
                '}';
    }
}

