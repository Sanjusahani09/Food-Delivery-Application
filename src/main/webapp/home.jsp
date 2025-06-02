<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.food.model.Restaurant, com.food.model.User,com.food.dao.RestaurantDAO,com.food.dao.Impl.RestaurantDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBites - Find Your Perfect Meal</title>
    <link rel="icon" href="bike2.png" type="image/x-icon">
    <link rel="stylesheet" href="home.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    
</head>
<body>
    <% 
    
	    RestaurantDAO rdaoimpl=new RestaurantDAOImpl();
		List<Restaurant> list = rdaoimpl.fetchAll();
		session.setAttribute("Rlist", list);
        User user = (User) session.getAttribute("user");
        String username = (user != null) ? user.getUsername() : "Guest";
    %>

    <!-- Navbar -->
    <nav class="navbar">
    <div class="logo">
        <a href="home.jsp">
            <img src="bike2.png" alt="QuickBites Logo" class="nav-logo">
            <span class="company-name">QuickBites</span>
        </a>
    </div>
    <div class="menu-toggle">☰</div>
    <div class="nav-links">
        <a href="#">Home</a>
        <a href="about.html">About</a>
        <% if (user != null) { %>
            <a href="GetAllOrders">My Orders</a>
            <a href="?logout=true">Log out</a>
        <% } else { %>
            <a href="UserLogin.jsp">Log in</a>
            <a href="index.jsp" class="signup-btn">Sign up</a>
        <% } %>
    </div>
</nav>


    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1 class="hero-title">Welcome, <%= username %></h1>
            <div class="search-container">
                <select  name="loaction" class="location-select">
                    <option>Bengaluru</option>
                    <option>Hyderabad</option>
                    <option>Chennai</option>
                </select>
                <input type="text"  id="query" class="search-input" placeholder="Search for restaurants, cuisines, or dishes..." oninput="filterRestaurants()">
                <a href="" style="text:decorattion:none;"><button class="search-btn">Search</button></a>
            </div>
        </div>
    </section>

   

    <!-- Restaurants Section -->
    <section class="restaurants-section">
        <h2 class="section-title">Popular Restaurants</h2>
        <div class="restaurant-grid">
            <% 
                List<Restaurant> Rlist = (List<Restaurant>)session.getAttribute("Rlist");
                if (Rlist != null) {
                    for (Restaurant r : Rlist) { 
            %>
            <a href="GetMenu?restaurantId=<%= r.getRestaurantId() %>" class="restaurant-card">
            <div class="restaurant-card">
		    <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>" class="restaurant-img">
		    <div class="restaurant-info">
		        <h3 class="restaurant-name"><%= r.getName() %></h3>
		        <p class="restaurant-details"><strong>Type:</strong> <%= r.getCuisineTime() %></p>
		        <p class="restaurant-details"><strong>Delivery Time:</strong> <%= r.getDeliveryTime() %> mins</p>
		        <p class="restaurant-details"><strong>Address:</strong> <%= r.getAddress() %></p>
		        <p class="restaurant-details"><strong>Ratings:</strong> ⭐ <%= r.getRatings() %></p>
		        <span class="status-badge <%= r.isActive() ? "status-active" : "status-inactive" %>">
		            <%= r.isActive() ? "Active" : "Inactive" %>
		        </span>
		    </div>
		</div>
		</a>

            <% 
                    }
                } else { 
            %>
            <p class="no-results">No restaurants available.</p>
            <% } %>
        </div>
        
        <section class="localities-section">
        <h2 class="localities-title">Popular localities in and around Bengaluru</h2>
        <div class="localities-grid">
            <div class="locality-card">
                <div class="locality-info">
                    <h3>Indiranagar</h3>
                    <p class="locality-places">616 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>Marathahalli</h3>
                    <p class="locality-places">1061 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>Whitefield</h3>
                    <p class="locality-places">1098 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>Koramangala 5th Block</h3>
                    <p class="locality-places">320 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>HSR</h3>
                    <p class="locality-places">1024 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>Jayanagar</h3>
                    <p class="locality-places">661 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>JP Nagar</h3>
                    <p class="locality-places">782 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card">
                <div class="locality-info">
                    <h3>Sarjapur Road</h3>
                    <p class="locality-places">836 places</p>
                </div>
                <span class="locality-arrow">›</span>
            </div>

            <div class="locality-card see-more-card">
                <span>see more</span>
                <span class="locality-arrow">›</span>
            </div>
        </div>
    </section>
    </section>
    <footer class="footer">
    <div class="footer-content">
        <div class="footer-brand">
            <div class="footer-logo">
                <img src="bike2.png" alt="QuickBites Logo">
                <span>QuickBites</span>
            </div>
            <p class="footer-description">
                Delivering happiness to your doorstep. QuickBites connects you with the best restaurants in your city.
            </p>
            <div class="footer-social">
                <a href="#"><i class="fab fa-facebook"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-linkedin"></i></a>
            </div>
        </div>

        <div class="footer-links">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Partner With Us</a></li>
                <li><a href="#">Career</a></li>
                <li><a href="#">Blog</a></li>
                <li><a href="#">Contact Us</a></li>
            </ul>
        </div>

        <div class="footer-links">
            <h3>Legal</h3>
            <ul>
                <li><a href="#">Terms & Conditions</a></li>
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Refund Policy</a></li>
                <li><a href="#">Cookie Policy</a></li>
                <li><a href="#">FAQs</a></li>
            </ul>
        </div>

        <div class="footer-contact">
            <h3>Contact Us</h3>
            <p><i class="fas fa-map-marker-alt"></i> 123 Food Street, Bengaluru, India</p>
            <p><i class="fas fa-phone"></i> +91 1234567890</p>
            <p><i class="fas fa-envelope"></i> support@quickbites.com</p>
        </div>
    </div>
	
	    <div class="footer-bottom">
	        <p>&copy; <%= new java.util.Date().getYear() + 1900 %> QuickBites. All rights reserved.</p>
	    </div>
	    <%
    if ("true".equals(request.getParameter("logout"))) {
        session.invalidate();
        response.sendRedirect("home.jsp");
    }
	%>
	</footer>
	<script>
        document.addEventListener('DOMContentLoaded', function() {
            const menuToggle = document.querySelector('.menu-toggle');
            const navLinks = document.querySelector('.nav-links');

            menuToggle.addEventListener('click', () => {
                navLinks.classList.toggle('active');
            });
        });
        
        function filterRestaurants() {
            const query = document.getElementById('query').value.toLowerCase();
            const restaurantCards = document.querySelectorAll('.restaurant-card');

            restaurantCards.forEach(card => {
                const restaurantName = card.querySelector('.restaurant-name').innerText.toLowerCase();

                // Show or hide card based on name match
                if (restaurantName.includes(query)) {
                    card.style.display = ''; // Show card
                } else {
                    card.style.display = 'none'; // Hide card
                }
            });
        }


        
        
        
        
    </script>
</body>
</html>