package hbv.example.controllers;

import hbv.example.dao.AdminDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SubmitVaccine", value = "/submitVaccine")
public class SubmitVaccine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String squantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(squantity);
        AdminDao adminDao = new AdminDao();
        adminDao.saveVaccine(id, name, manufacturer, quantity);
    }
}