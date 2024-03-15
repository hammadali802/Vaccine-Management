package hbv.example.controllers;

import hbv.example.dao.BookingDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetVaccines", value = "/vaccines")
public class GetVaccines extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getId = request.getParameter("id");


        int id = Integer.parseInt(getId);

        JSONObject appointmentsJSON = null;
        try {
            appointmentsJSON = BookingDao.getAllVaccinesAsJSON(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(appointmentsJSON);
        // Set response content type to JSON
        response.setContentType("text/json");
        PrintWriter writer = response.getWriter();
        writer.println(appointmentsJSON);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}