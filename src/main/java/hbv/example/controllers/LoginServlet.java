package hbv.example.controllers;

import hbv.example.dao.Counters;
import hbv.example.dao.UserDao;
import hbv.example.model.User;
import jakarta.servlet.RequestDispatcher;
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
Counters counter = new Counters();
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
        counter.incrementPageViews("Page_called","Einloggen");


        String email = request.getParameter("email");
        String password = request.getParameter("password");


        UserDao userDao = new UserDao();
        User user = userDao.isValidUser(email, password);

        if (user != null) {
            counter.redisCounter("sessions_created");
            session.setAttribute("user",user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("booking.html");
            dispatcher.forward(request, response);
            //System.out.println("Hi - "+username);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html?error=1");
            dispatcher.forward(request, response);
        }
    }


}

