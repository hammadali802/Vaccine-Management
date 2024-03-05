package com.example.controllers;


import com.example.dao.UserDao;
import com.example.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/adduser.swe")
public class UserServlet extends HttpServlet {
    public UserServlet(){
        super();
    }
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        if (session == null) {
//            response.sendRedirect("login.html");
//            return;
//        }
//          HttpSession session = request.getSession();


        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String dateofbirth = request.getParameter("birthdate");


        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String postalcode = request.getParameter("postalcode");
int userId = -1;
        System.out.println(firstname);

        User user = new User(firstname, lastname, dateofbirth, email,password,city,postalcode);
        UserDao userDao = new UserDao();
        if (userDao.isEmailExists(email)) {
        userId = userDao.addUser(user);

        } else{
            response.sendRedirect("register.html?error=1");

        }
//        session.setAttribute("userId",userId);


        System.out.println(userId);

        if ( userId > 0 ) {
            response.sendRedirect("login.html?registration=success");
        } else {
            response.sendRedirect("register.html?error=1");
        }


    }

}