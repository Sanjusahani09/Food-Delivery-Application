package com.tap.foodapp;

import java.io.IOException;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.dao.Impl.OrderDAOImpl;
import com.food.model.Order;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class GetAllOrders
 */
@WebServlet("/GetAllOrders")
public class GetAllOrders extends HttpServlet {
	
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 User user=(User)session.getAttribute("user");
		 int userId=user.getUserid();
		 if(request.getCookies()[0].getValue()!=null) {
				OrderDAO orderdaoimpl=new OrderDAOImpl();
				List<Order> order=orderdaoimpl.fetchAllUsingUserId(userId);
				session.setAttribute("Orderlist", order);
				response.sendRedirect("MyOrder.jsp");
			}else {
				response.sendRedirect("index.jsp");
			}
	}

}
