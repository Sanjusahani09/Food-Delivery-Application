<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.food.model.Cart, com.food.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBites-Checkout</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="checkout.css">
</head>
<body>
    <div class="container">
        <div class="checkout-wrapper">
            <!-- Left side - Checkout Form -->
            <div class="checkout-form">
                <h1>Checkout</h1>
                <form  action="CheckoutServlet" method="post" id="checkoutForm">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" id="name" name="name" required placeholder="John Doe">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" name="email" required placeholder="john@example.com">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="address">Delivery Address</label>
                        <textarea id="address" name="address" required placeholder="123 Main St, City, Country"></textarea>
                    </div>

                    <div class="form-group">
                        <label>Payment Method</label>
                        <div class="payment-options">
                            <label class="payment-option">
                                <input type="radio" name="paymentMethod" value="upi" checked>
                                <span class="checkmark"></span>
                                <img src="https://cdn-icons-png.flaticon.com/512/6963/6963703.png" alt="UPI" class="payment-icon">
                                UPI
                            </label>
                            <label class="payment-option">
                                <input type="radio" name="paymentMethod" value="card">
                                <span class="checkmark"></span>
                                <img src="https://cdn-icons-png.flaticon.com/512/179/179457.png" alt="Card" class="payment-icon">
                                Card
                            </label>
                            <label class="payment-option">
                                <input type="radio" name="paymentMethod" value="cod">
                                <span class="checkmark"></span>
                                <img src="https://cdn-icons-png.flaticon.com/512/2331/2331941.png" alt="COD" class="payment-icon">
                                Cash on Delivery
                            </label>
                        </div>
                    </div>

                    <div id="cardDetails" class="payment-details hidden">
                        <div class="form-group">
                            <label for="cardNumber">Card Number</label>
                            <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456">
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="cardExpiry">Expiry Date</label>
                                <input type="text" id="cardExpiry" name="cardExpiry" placeholder="MM/YY">
                            </div>
                            <div class="form-group">
                                <label for="cardCvv">CVV</label>
                                <input type="text" id="cardCvv" name="cardCvv" placeholder="123">
                            </div>
                        </div>
                    </div>

                    <div id="upiDetails" class="payment-details">
                        <div class="form-group">
                            <label for="upiId">UPI ID</label>
                            <input type="text" id="upiId" name="upiId" placeholder="yourname@upi">
                        </div>
                    </div>

                    <!-- Order Summary -->
                    <div class="order-summary">
                        <h2>Order Summary</h2>
                        <% //double %>
                        <% Cart cart = (Cart) session.getAttribute("cart");
                           if(cart == null || cart.getItem() == null) { %>
                            <p class="empty-cart">Your cart is empty<br>Add items to your cart to view them here</p>
                        <% } else {
                            for (Map.Entry<Integer, CartItem> entry : cart.getItem().entrySet()) {
                                CartItem item = entry.getValue(); 
                                %>
                                <div class="order-item">
                                    <span><%= item.getName() %></span>
                                    <span>₹<%= item.getPrice() %></span>
                                </div>
                        <% }
                           } %>

                        <div class="order-total">
                            <% Double totalAmountObject = (Double) session.getAttribute("totalAmount");
                               double totalAmount = totalAmountObject != null ? totalAmountObject : 0.0; %>
                            <span>Total Amount</span>
                            <span>₹<%= String.format("%.2f", totalAmount) %></span>
                        </div>
                    </div>

                    <button type="submit" id="submitButton" class="place-order-btn">
                        <img src="https://cdn-icons-png.flaticon.com/512/2435/2435281.png" alt="Order" class="button-icon">
                        Place Order
                    </button>
                </form>
            </div>
        </div>
    </div>

    <!-- Animation Elements -->
    <div class="overlay"></div>
    <img src="bike2.png" alt="Delivery" class="delivery-animation">
    <div class="order-success">
        <img src="https://cdn-icons-png.flaticon.com/512/190/190411.png" alt="Success" class="success-icon">
        <h2>Order Placed Successfully!</h2>
        <p>Your delicious food is being prepared.</p>
        <div class="success-buttons">
            <a href="checkorderstatus.jsp"><button class="success-button check-status">Check Order Status</button></a>
            <a href="GetAllRestaurants"><button class="success-button go-restaurant">Go to Restaurant</button></a>
        </div>
    </div>
    <% 
    Boolean orderConfirmed = (Boolean) session.getAttribute("orderConfirmed");
    if (orderConfirmed != null && orderConfirmed) {
        session.removeAttribute("orderConfirmed");
%>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const orderSuccess = document.querySelector('.order-success');
            const overlay = document.querySelector('.overlay');

            // Show success popup
            overlay.classList.add('active');
            orderSuccess.classList.add('active');
        });
    </script>
<% 
    } 
%>

    <script src="checkout.js"></script>
</body>
</html>