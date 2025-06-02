package com.tap.foodapp;

import java.io.IOException;

import com.food.dao.MenuDAO;
import com.food.dao.Impl.MenuDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

       
        String action = request.getParameter("action");
        System.out.println(action);

      
        MenuDAO menudao;
        try {
            menudao = new MenuDAOImpl();

            
            try {
                int menuId = Integer.parseInt(request.getParameter("menuId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                session.setAttribute("quantity", quantity);

                if ("add".equals(action)) {
                    Menu menuItem = menudao.fetchOne(menuId);
                    if (menuItem != null) {
                        CartItem cartItem = new CartItem(menuItem.getMenuId(), menuItem.getRestaurantId(), menuItem.getName(), menuItem.getPrice(), quantity,menuItem.getImagePath(),menuItem.getDescription());
                        cart.addItem(cartItem);
                    }
                } else if ("remove".equals(action)) {
                    cart.removeItem(menuId);
                } else if ("update".equals(action)) {
                    cart.updateItem(menuId, quantity);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=Invalid+input");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Internal+server+error");
            return;
        }

        
        session.setAttribute("cart", cart);
        response.sendRedirect("Cart.jsp");
    }
}
