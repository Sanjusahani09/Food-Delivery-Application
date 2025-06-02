package com.tap.foodapp;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetAllRestaurants")
public class GetAllRestaurants extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		if(request.getCookies()[0].getValue()!=null) {
			
			response.sendRedirect("home.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}

}
