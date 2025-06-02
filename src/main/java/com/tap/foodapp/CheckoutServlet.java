package com.tap.foodapp;

import java.io.IOException;
import java.util.Collection;

import com.food.dao.OrderDAO;
import com.food.dao.OrderItemDAO;
import com.food.dao.Impl.OrderDAOImpl;
import com.food.dao.Impl.OrderItemDAOImpl;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.OrderItem;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		User user=(User) session.getAttribute("user");
		
		if(cart!=null && !cart.getItem().isEmpty()) {
			String paymentMethod=request.getParameter("paymentMethod");
			String restaurantId=(String)session.getAttribute("restaurantId");
			Order order=new Order();
			order.setUserId(user.getUserid());
			order.setRestaurantId(Integer.parseInt(restaurantId));
			order.setPaymentMode(paymentMethod);
			order.setStatus("pending");
			double totalAmount=0;
			for(CartItem item: cart.getItem().values()) {
				totalAmount+=item.getPrice()*item.getQuantity();
				
			}
			order.setTotal_amount(totalAmount);
			
			OrderDAO orderdaoimpl=new OrderDAOImpl();
			orderdaoimpl.insert(order);
			System.out.println(order);
			
			
			Collection<CartItem> cartitem=cart.getItem().values();
			int orderId=order.getOrderId();
			session.setAttribute("orderid", orderId);
			for(CartItem cartItem:cartitem) {
				int menuId=cartItem.getItemId();
				int quantity=cartItem.getQuantity();
				double itemTotal=(double)session.getAttribute("total");
				
				System.out.println(menuId);
				System.out.println(quantity);
				System.out.println(orderId);
				System.out.println(itemTotal);
				
				OrderItem orderitem=new OrderItem();
				orderitem.setMenuId(menuId);
				orderitem.setOrderId(orderId);
				orderitem.setQuantity(quantity);
				orderitem.setItemTotal(itemTotal);
				OrderItemDAO oderitemdao=new OrderItemDAOImpl();
				oderitemdao.insert(orderitem);
			}
			
//			int menuId=cart.getItem().values().iterator().next().getItemId();
//			int quantity=cart.getItem().values().iterator().next().getQuantity();
//			double itemTotal=(double)session.getAttribute("total");
			
			
			//session.removeAttribute("cart");
			session.setAttribute("order", order);
			session.setAttribute("orderConfirmed", true);
			response.sendRedirect("Checkout.jsp");
		}
		else {
			response.sendRedirect("Cart.jsp");
		}
	}

}
