<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.model.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            padding: 2rem;
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

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            color: #2d3748;
            font-size: 2.5rem;
            margin-top: 6rem;
            position: relative;
            padding-bottom: 1rem;
        }

        h1::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 100px;
            height: 4px;
            background: linear-gradient(90deg, #667eea 0%, #764ba2 100%);
            border-radius: 2px;
        }

        .table-container {
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            margin: 2rem auto;
            transition: transform 0.3s ease;
        }

        .table-container:hover {
            transform: translateY(-5px);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            padding: 1.2rem 1.5rem;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background: #f8fafc;
            color: #4a5568;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.875rem;
            letter-spacing: 0.05em;
        }

        tr:hover {
            background-color: #f7fafc;
        }

        td {
            color: #4a5568;
            font-size: 0.95rem;
        }

        .status {
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-weight: 500;
            font-size: 0.875rem;
            text-transform: capitalize;
        }

        .status-pending {
            background-color: #fff8e1;
            color: #f59e0b;
        }

        .status-completed {
            background-color: #dcfce7;
            color: #22c55e;
        }

        .status-cancelled {
            background-color: #fee2e2;
            color: #ef4444;
        }

        .empty-message {
            text-align: center;
            padding: 3rem;
            color: #64748b;
            font-size: 1.1rem;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        @media (max-width: 768px) {
            .table-container {
                overflow-x: auto;
            }
            
            th, td {
                padding: 1rem;
                min-width: 120px;
            }

            h1 {
                font-size: 2rem;
            }
        }

        /* Animation for table rows */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        tr {
            animation: fadeIn 0.3s ease-out forwards;
        }
    </style>
</head>
<body>
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
        <h1>Your Orders</h1>
        <%
            List<Order> orderList = (List<Order>) session.getAttribute("Orderlist");

            if (orderList != null && !orderList.isEmpty()) {
        %>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Restaurant</th>
                            <th>Order Date</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                            <th>Payment Mode</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Order order : orderList) {
                                String statusClass = "";
                                switch(order.getStatus().toLowerCase()) {
                                    case "pending":
                                        statusClass = "status-pending";
                                        break;
                                    case "completed":
                                        statusClass = "status-completed";
                                        break;
                                    case "cancelled":
                                        statusClass = "status-cancelled";
                                        break;
                                }
                        %>
                            <tr style="animation-delay: <%= orderList.indexOf(order) * 0.1 %>s">
                                <td>#<%= order.getOrderId() %></td>
                                <td><%= order.getRestaurantId() %></td>
                                <td><%= order.getOrderDate() %></td>
                                <td>₹<%= String.format("%.2f", order.getTotal_amount()) %></td>
                                <td><span class="status <%= statusClass %>"><%= order.getStatus() %></span></td>
                                <td><%= order.getPaymentMode() %></td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        <%
            } else {
        %>
            <div class="empty-message">
                <p>No orders found. Start ordering delicious food!</p>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>