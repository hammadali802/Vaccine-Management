package hbv.example.controllers;
//
//
import hbv.example.dao.AppointmentDao;
import hbv.example.dao.Counters;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//
//@WebServlet(name = "AppointmentData", value = "/appointmentData")
//public class AppointmentData extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Appointment Data</title></head><body>");
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        try {
//            String htmlTable = AppointmentDao.AppointmentDataToHtmlTable(id);
//            out.println(htmlTable);
//        } catch (SQLException e) {
//            out.println("<p>Error retrieving appointment data: " + e.getMessage() + "</p>");
//            e.printStackTrace();
//        }
//
//        out.println("</body></html>");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;

@WebServlet(name = "AppointmentData", value = "/appointmentData")
public class AppointmentData extends HttpServlet {

    Counters counters = new Counters();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        counters.redisCounter("qr_code");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Appointment Data</title></head><body>");

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            String htmlTable = AppointmentDao.AppointmentDataToHtmlTable(id);
            out.println(htmlTable);
        } catch (SQLException e) {
            out.println("<p>Error retrieving appointment data: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }



        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
