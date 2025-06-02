<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.food.model.Cart, com.food.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        
        .navbar {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 5%;
            background-color: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(10px);
            z-index: 1000;
            transition: background-color 0.3s ease;
        }
        .navbar a{
        	text-decoration:none;
        	display:flex;
        	align-item:center;
        }
        .navbar:hover {
            background-color: rgba(255, 255, 255, 0.95);
        }
        .nav-logo{
        	height:50px;
        	width:auto;
        	margin-right:10px;
        }
        .company-name {
            font-size: 2rem;
            font-weight: 700;
            background: linear-gradient(45deg, #FF6B6B, #4ECDC4);
            -webkit-background-clip: text;
            background-clip: text;
            -webkit-text-fill-color: transparent;
            text-decoration: none;
        }
        .cart-icon {
            font-size: 34px;
            color: #007bff;
            cursor: pointer;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: 2fr 1fr;
            gap: 20px;
        }

        .cart-section {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-top:80px;
        }

        .cart-header {
            font-size: 20px;
            margin-bottom: 20px;
            font-weight: bold;
            color: #333;
        }

        .cart-item {
            display: grid;
            grid-template-columns: auto 1fr auto;
            gap: 20px;
            padding: 15px 0;
            border-bottom: 1px solid #eee;
        }

        .product-image {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 8px;
        }
        .product-image img{
        	width: 100px;
        	height:100px;
        	
        }

        .product-details h1 {
            margin: 0;
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        .description {
            color: #666;
            font-size: 14px;
            margin: 10px 0;
        }

        .price {
            font-weight: bold;
            font-size: 16px;
            color: #e74c3c;
            margin: 10px 0;
        }

        .quantity {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .quantity input {
            width: 51px;
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .remove-btn {
            background: #e74c3c;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .remove-btn:hover {
            background: #c0392b;
        }

        .summary-section {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-top:80px;
        }

        .summary-header {
            font-size: 20px;
            margin-bottom: 20px;
            font-weight: bold;
        }

        .price-details {
            margin-bottom: 20px;
        }

        .price-row {
            display: flex;
            justify-content: space-between;
            margin: 10px 0;
        }

        .checkout-btn {
            width: 100%;
            background: #4CAF50;
            color: white;
            padding: 15px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 20px;
        }

        .checkout-btn:hover {
            background: #3e8e41;
        }
        .summary-section a{
        	width: 100%;
            background: #4CAF50;
            color: white;
            padding: 15px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 20px;
            text-decoration:none;
        }
    </style>
</head>
<body>
	<nav class="navbar">
    <a href="home.jsp">
            <img src="bike2.png" alt="QuickBites Logo" class="nav-logo">
            <span class="company-name">QuickBites</span>
        </a>
   
    <div style="position: relative;">
         <div class="cart-icon" onclick="viewCart()">&#128722;</div>
        <!-- Cart badge to show the number of items -->
        <span id="cart-badge" style="
            position: absolute;
            top: -5px;
            right: -10px;
            background-color: red;
            color: white;
            font-size: 12px;
            font-weight: bold;
            padding: 2px 6px;
            border-radius: 50%;
            display: none;
        ">0</span>
    </div>
</nav>
    <div class="container">
        <div class="cart-section">
            <%
            	int resId=0;
                Cart cart = (Cart) session.getAttribute("cart");
                double totalAmount = 0; // Declare totalAmount here, so it is accessible later
                if (cart == null || cart.getItem() == null || cart.getItem().isEmpty()) {
            %>
                <div class="cart-header">Your cart is empty</div>
                <p>Add items to your cart to view them here.</p>
            <%
                } else {
                    for (Map.Entry<Integer, CartItem> entry : cart.getItem().entrySet()) {
                        CartItem item = entry.getValue();
                        totalAmount += item.getPrice() * item.getQuantity(); // Calculate totalAmount inside the loop
            %>
                <div class="cart-item">
                    <div class="product-image">
                        <img src="<%=item.getImagePath() %>" alt="<%= item.getName() %>">
                    </div>
                    <div class="product-details">
                        <h1><%= item.getName() %></h1>
                        <p class="price">₹<%= item.getPrice() %></p>
                        <p class="description"><%= item.getDescription() %>.</p>
                        <%resId=item.getRestaurantId(); %>
                        <div class="quantity">
                            <form method="post" action="CartServlet" class="quantity-control">
						        <input type="hidden" name="action" value="update">
						        <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
						        <button type="submit" name="quantity" value="<%= Math.max(1, item.getQuantity() - 1) %>" class="quantity-btn">-</button>
						        <input type="number" value="<%= item.getQuantity() %>" readonly>
						        <button type="submit" name="quantity" value="<%= item.getQuantity() + 1 %>" class="quantity-btn">+</button>
						    </form>
                        </div>
                        <p> Total :₹<%=item.getPrice() * item.getQuantity() %></p>
                        <% double total=item.getPrice()*item.getQuantity(); 
                        	session.setAttribute("total",total);
                        %>
                        <input type="hidden" name="total" value="<%=item.getPrice()*item.getQuantity()%>">
                        <form method="post" action="CartServlet" style="margin-top: 10px;">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="quantity" value="<%=item.getQuantity() %>">
                            <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
                            <button type="submit" class="remove-btn">Remove</button>
                        </form>
                    </div>
                </div>
            <%
                    }
                }
            %>
            
        </div>
        <%
            if (cart != null && !cart.getItem().isEmpty()) {
        %>
        <div class="summary-section">
            <h2 class="summary-header">Price Details</h2>
            <div class="price-details">
                <div class="price-row">
                    <span>Sub Total:</span>
                    <span>₹<%= String.format("%.2f", totalAmount) %></span>
                </div>
                <div class="price-row">
                    <span>Taxes and Charges:</span>
                    <span>₹<%= String.format("%.2f", totalAmount * 0.05) %></span>
                </div>
                <div class="price-row">
                    <strong>Grand Total:</strong>
                    <strong>₹<%= String.format("%.2f", totalAmount * 1.05) %></strong>
                      <%  
        	           session.setAttribute("totalAmount",totalAmount);
        	           System.out.println(totalAmount);
                    %>
                    
                </div>
            </div>
            <form action="Checkout.jsp" method="post">
                <button type="submit" class="checkout-btn">Proceed to Checkout</button>
            </form> <br><br>
            <a href="GetMenu?restaurantId=<%=resId %>" class="restaurant-card">Add more</a>
        </div>
        <%
            }
        %>
      
    </div>
</body>
</html>
