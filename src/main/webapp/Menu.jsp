<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.food.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu List</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', Arial, sans-serif;
            margin: 0;
            padding-top: 70px; /* Add padding to account for the fixed navbar */
            background-color: #f8f9fa;
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
        .restaurant-name {
            text-align: center;
            margin: 20px 0;
            font-size: 28px;
            font-weight: bold;
            color: #333;
        }
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            padding: 0 20px;
        }
        .menu-card {
            border: 1px solid #ddd;
            border-radius: 12px;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 300px;
            padding: 20px;
            text-align: center;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        .menu-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }
        .menu-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 8px;
        }
        .menu-card h3 {
            margin: 15px 0 10px;
            font-size: 20px;
            color: #333;
        }
        .menu-card p {
            margin: 10px 0;
            font-size: 14px;
            color: #666;
        }
        .menu-card .price {
            font-weight: bold;
            font-size: 18px;
            color: #28a745;
            margin: 15px 0;
        }
        .menu-card .availability {
            font-size: 14px;
            margin-bottom: 15px;
            color: #007bff;
        }
        .menu-card button {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.2s ease;
        }
        .menu-card button a{
        	text-decoration:none;
        }
        .menu-card button:hover {
            background-color: #0056b3;
        }

        /* Responsive Styles */
        @media (max-width: 1024px) {
            .menu-container {
                justify-content: space-around;
            }
            .menu-card {
                width: 45%; /* Adjust width for tablets */
            }
        }

        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                padding: 10px;
            }
            .company-name {
                font-size: 1.5rem;
            }
            .menu-container {
                flex-direction: column;
                align-items: center;
                padding: 0 10px;
            }
            .menu-card {
                width: 80%; /* Full width on smaller screens */
            }
            .restaurant-name h1 {
                font-size: 24px;
            }
        }

        @media (max-width: 480px) {
            .menu-card {
                width: 100%;
                padding: 15px;
            }
            .menu-card h3 {
                font-size: 18px;
            }
            .menu-card .price {
                font-size: 16px;
            }
            .menu-card button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

<% String loggedInUser=(String) session.getAttribute("loggedInUser"); %>

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

<%
    // Get restaurant name from session
    List<Menu> Mlist = (List<Menu>) session.getAttribute("MenuList");
%>

<div class="restaurant-name">
    <h1>Restaurant Menu</h1>
</div>

<%
    if (Mlist != null && !Mlist.isEmpty()) {
%>
    <div class="menu-container">
        <% for (Menu menu : Mlist) { %>
        <div class="menu-card">
            <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>">
            <h3><%= menu.getName() %></h3>
            <p><%= menu.getDescription() %></p>
            <p class="price">Price: ₹<%= menu.getPrice() %></p>
            <% if(loggedInUser !=null) { %>
            <form action="CartServlet" method="post">
            	<input type="hidden" name="action" value="add">
	            <input type="hidden" name="menuId" value="<%=menu.getMenuId() %>">
	            <input type="hidden" name="quantity" value="1"> 
            	<button onclick="addToCart('<%= menu.getMenuId() %>')">Add to Cart</button>
            </form>
            <% } else { %>
            <button onclick="promptLogin()">Add to Cart</button>
        <% } %>
        </div>
        <% } %>
    </div>
<%
    } else {
%>
    <p style="text-align: center; font-size: 18px; color: #666;">No menu details available.</p>
<%
    }
%>

<script>
    let cartItemCount = 0; // Initialize the cart count to zero

    // Function to handle adding items to the cart
    function addToCart(menuId) {
        cartItemCount++; // Increment the cart count
        updateCartBadge(); // Update the cart badge
        
        // Add AJAX call here for server-side cart functionality if needed
    }

    // Function to update the cart badge
    function updateCartBadge() {
        const cartBadge = document.getElementById("cart-badge");
        cartBadge.innerText = cartItemCount; // Update the badge text
        cartBadge.style.display = cartItemCount > 0 ? "inline-block" : "none"; // Show or hide the badge
    }
    function promptLogin() {
        alert("Please log in to add items to your cart.");
        // Optionally redirect to login page
        window.location.href = "UserLogin.jsp";
    }
    
</script>

</body>
</html>
