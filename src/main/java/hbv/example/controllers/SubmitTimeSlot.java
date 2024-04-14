package hbv.example.controllers;

import hbv.example.dao.AdminDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SubmitTimeSlot", value = "/submitTimeSlot")
public class SubmitTimeSlot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String capacity = request.getParameter("capacity");
        AdminDao adminDao = new AdminDao();
        adminDao.saveTimeSlot( startTime, endTime, id, Integer.parseInt(capacity));
    }
}