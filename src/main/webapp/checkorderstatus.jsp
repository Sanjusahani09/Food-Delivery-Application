<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.food.model.*, com.food.model.CartItem" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Tracking</title>
    <link rel="stylesheet" href="checkorderstatus.css">
    <style>
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
        .nav-links{
        	display:flex;
        	align-items:center;
        	gap:1rem;
        }
        .nav-button{
        	text-decoration:none;
        	color:#333;
        	font-size: 1rem;
		    font-weight: bold;
		    padding: 0.5rem 1rem;
		    border: 2px solid #1a1a1a;
		    border-radius: 5px;
		    transition: all 0.3s ease
        }
        .nav-button:hover{
        	background-color:#4ECDC4;
        	color:#fff;
        	border-color:#4ECDC4;
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
            display:flex;
            align-items:center;
            justify-content:center;
            width:40px;
            object-fit:cover;
            height:40px;
            overflow:hidden;
            cursor: pointer;
        }
        .cart-icon img{
        	max-width:100%;
        	max-height:100%;
        	weight:auto;
        	height:auto;

        }
        .order-card {
        margin-top:50px;
        }
    </style>
</head>
<body>
	<% Order order=new Order();
	
	%>
	
	<nav class="navbar">
    <a href="home.jsp">
            <img src="bike2.png" alt="QuickBites Logo" class="nav-logo">
            <span class="company-name">QuickBites</span>
        </a>
   	<div class="nav-links">
   	<a href="home.jsp" class="nav-button">Home</a>
    <div style="position: relative;">
         <div class="cart-icon"><img src="help.png"></div>
        <!-- Cart badge to show the number of items -->
        
    </div>
    </div>
</nav>
    <div class="container">
        <div class="order-card">
            <h1>Order Status</h1>
            <div class="order-details">
            <% int orderId=(int)session.getAttribute("orderid"); %>
                <p class="order-id">Order: <%=orderId %></p>
                <p class="estimated-time">Estimated Delivery: 30 mins</p>
            </div>

            <div class="status-container">
                <div class="status-line">
                    <div class="status-progress"></div>
                </div>
                <div class="bike-container">
                    <img src="bike2.png" alt="Delivery bike" class="bike">
                </div>
                <div class="status-steps">
                    <div class="status-step" data-step="1">
                        <div class="step-icon">🏪</div>
                        <p>Preparing</p>
                    </div>
                    <div class="status-step" data-step="2">
                        <div class="step-icon">📦</div>
                        <p>Packed</p>
                    </div>
                    <div class="status-step" data-step="3">
                        <div class="step-icon">🚲</div>
                        <p>On Road</p>
                    </div>
                    <div class="status-step" data-step="4">
                        <div class="step-icon">✅</div>
                        <p>Delivered</p>
                    </div>
                </div>
            </div>

            <div class="order-items">
            <h2>Order Items</h2>
            <%
            	Cart cart=(Cart) session.getAttribute("cart");
            	if(cart == null || cart.getItem()==null || cart.getItem().isEmpty()){
            %>
            	<h2>Your cart is empty</h2>
            	<p>Add items to your cart to view them here.</p>
            <% 
            	} else {
                    for (Map.Entry<Integer, CartItem> entry : cart.getItem().entrySet()) {
                        CartItem item = entry.getValue();
            
            %>
                
                <div class="item">
                    <span><%=item.getName() %></span>
                    <span>₹<%=item.getPrice() %></span>
                </div>
               
              <%
                    }
                }
            %>
             <div class="total">
             <% Double totalAmountObject = (Double) session.getAttribute("totalAmount");
                double totalAmount = totalAmountObject != null ? totalAmountObject : 0.0; %>
                    <span>Total</span>
                    <span><%=totalAmount %></span>
                </div>
            <%session.removeAttribute("cart");%>
            </div>
        </div>
    </div>
    <script src="checkorderstatus.js"></script>
</body>
</html>