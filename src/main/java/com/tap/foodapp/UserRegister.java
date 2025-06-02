package com.tap.foodapp;

import java.io.IOException;

import com.food.dao.Impl.UserDAOImpl;
import com.food.model.User;
import com.food.secure.SecureData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       
        String username = SecureData.encrypt(request.getParameter("username"));
        String password = SecureData.encrypt(request.getParameter("password"));
        String conpassword = SecureData.encrypt(request.getParameter("confirmpassword"));
        String email = SecureData.encrypt(request.getParameter("email"));
        String address = SecureData.encrypt(request.getParameter("address"));

       
        UserDAOImpl userimpl = new UserDAOImpl();
        
        try {
           
            if (!password.equals(conpassword)) {
                request.setAttribute("status", "passwordMismatch");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

          
            User existingUser = userimpl.fetchEmail(email);
            if (existingUser != null) {
                request.setAttribute("status", "emailExists");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            
            User u = new User(username, password, email, address);
            
            
            int result = userimpl.insert(u);
            if (result != 0) {
                
                HttpSession session = request.getSession();
                session.setAttribute("status", "success");
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("status", "failure");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "failure");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}