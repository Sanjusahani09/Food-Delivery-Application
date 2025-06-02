package com.tap.foodapp;

import java.io.IOException;

import com.food.dao.Impl.UserDAOImpl;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	
	String encrypt(String str) {
    	StringBuffer sb=new StringBuffer();
    	for(int i=0; i<str.length(); i++) {
    		sb.append((char)(str.charAt(i)+2));
    	}
    	return sb.toString();
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=encrypt(req.getParameter("email"));
		String password=encrypt(req.getParameter("newPassword"));
		String confirmpassword=encrypt(req.getParameter("confirmPassword"));
		if(password.equals(confirmpassword)) {
			UserDAOImpl userimpl=new UserDAOImpl();
			User u=userimpl.fetchEmail(email);
			if(u!=null) {
				userimpl.updatePassword(email, confirmpassword);
				resp.sendRedirect("UserLogin.jsp");
			}
			else {
				resp.sendRedirect("checkEmail.html");
			}
			
		}else {
			resp.sendRedirect("pswdMisMatch.html");
		}
	}

}
