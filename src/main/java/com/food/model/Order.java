package com.food.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private double total_amount;
    private String status;
    private String paymentMode;
    private Date orderDate; // Add orderDate field

    public Order() {
        super();
    }

    public Order(int orderId, int userId, int restaurantId, double total_amount, String status, String paymentMode, Date orderDate) {
        super();
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.total_amount = total_amount;
        this.status = status;
        this.paymentMode = paymentMode;
        this.orderDate = orderDate;
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getOrderDate() { // Getter for orderDate
        return orderDate;
    }

    public void setOrderDate(Date orderDate) { // Setter for orderDate
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return orderId + "    " + userId + "    " + restaurantId + "    " 
                + total_amount + "    " + status + "    " + paymentMode + "    " + orderDate;
    }
}
