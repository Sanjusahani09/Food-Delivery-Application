package com.tap.foodapp;

import java.io.IOException;

import com.food.dao.Impl.UserDAOImpl;
import com.food.model.User;
import com.food.secure.SecureData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=SecureData.encrypt((req.getParameter("email")));
		String password=SecureData.encrypt((req.getParameter("password")));
		UserDAOImpl userimpl=new UserDAOImpl();
    	User u=userimpl.fetchEmailPassword(email,password);
    	
    	if(u!=null) {
    		HttpSession session=req.getSession();
    		session.setAttribute("user", u);
    		session.setAttribute("loggedInUser", u.getEmail());
    		Cookie cookie=new Cookie("email",email);
        	resp.addCookie(cookie);
    		req.getRequestDispatcher("GetAllRestaurants").forward(req, resp);
    	}else {
    		resp.sendRedirect("InvalidLogin.html");
    	}
		
	}
}
