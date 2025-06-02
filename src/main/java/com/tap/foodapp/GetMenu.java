package com.tap.foodapp;

import java.io.IOException;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.dao.Impl.MenuDAOImpl;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class GetMenu
 */
@WebServlet("/GetMenu")
public class GetMenu extends HttpServlet {
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve the restaurantId from the request parameters
        String restaurantId = request.getParameter("restaurantId");
        System.out.println(restaurantId);
        
        if (restaurantId != null && !restaurantId.isEmpty()) {
            MenuDAO menuDAO = new MenuDAOImpl();
            List<Menu> menuList = menuDAO.fetchMenuByRestaurantId(Integer.parseInt(restaurantId));
            
            HttpSession session = request.getSession();
            session.setAttribute("MenuList", menuList);
            session.setAttribute("restaurantId", restaurantId);
            
            response.sendRedirect("Menu.jsp");
        }
	}

}
