package hbv.example.controllers;

import hbv.example.dao.AdminDao;
import hbv.example.model.Admin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminLogin", value = "/adminLogin")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        AdminDao userDao = new AdminDao();
        Admin admin = userDao.isValidAdmin(email, password);

        if (admin != null) {
            session.setAttribute("admin",admin);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.html");
            dispatcher.forward(request, response);
            //System.out.println("Hi - "+username);
        } else {
            response.sendRedirect("login.html?error=1");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html?error=1");
//            dispatcher.forward(request, response);
        }
    }
}