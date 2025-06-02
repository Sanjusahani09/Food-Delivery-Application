package com.tap.foodapp;

import java.io.IOException;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.dao.Impl.RestaurantDAOImpl;
import com.food.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchRestaurants
 */
@WebServlet("/SearchRestaurants")
public class SearchRestaurants extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String location = request.getParameter("location");
        String query = request.getParameter("query");

        // Use DAO to fetch matching restaurants
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> matchingRestaurants = restaurantDAO.searchRestaurants(query);

        // Set the results in session or request scope
        request.setAttribute("Rlist", matchingRestaurants);

        // Forward to the same JSP or a new results page
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

}
