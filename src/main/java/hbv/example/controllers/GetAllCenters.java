package hbv.example.controllers;

import hbv.example.dao.BookingDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetAllCenters", value = "/centers")

public class GetAllCenters extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//
        JSONObject centersJson = null;
        try {
              centersJson = BookingDao.getAllCentersAsJSON();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(appointmentsJSON);
//        // Set response content type to JSON



        response.setContentType("text/json");

        PrintWriter out = response.getWriter();
         out.println(centersJson.toString());
         out.flush();
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}