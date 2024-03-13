package com.example.controllers;

import com.example.dao.UserDao;
import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet(){
        super();
    }
//    Integer userId = null;
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//        String userIdStr = request.getParameter("userId");
//        if (userIdStr != null) {
//            userId = Integer.parseInt(userIdStr);
//        }
//    } catch (NumberFormatException e) {
//        // Handle invalid user ID format (e.g., log error, redirect to error page)
//    }
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        UserDao userDao = new UserDao();
        User user = userDao.isValidUser(email, password);

        if (user != null) {
            session.setAttribute("user",user);
            response.sendRedirect("booking.html");
            //System.out.println("Hi - "+username);
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }


}

