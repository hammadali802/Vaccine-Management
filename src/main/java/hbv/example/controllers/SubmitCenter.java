package hbv.example.controllers;
import hbv.example.dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SubmitCenter", value = "/submitCenter")
public class SubmitCenter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
//        String password = request.getParameter("quantity");
//        System.out.println(id);
        System.out.println(name);
        System.out.println(location);

        AdminDao adminDao = new AdminDao();
        adminDao.saveCenter(name,location);
//        System.out.println(password);
    }
}