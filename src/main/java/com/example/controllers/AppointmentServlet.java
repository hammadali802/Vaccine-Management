package com.example.controllers;

import com.example.dao.AppointmentDao;
import com.example.dao.RelativeDao;
import com.example.model.Appointment;
import com.example.model.Relative;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;



@WebServlet(name = "AppointmentServlet", value = "/appointment.swe")
public class AppointmentServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String bookingType = request.getParameter("bookingType");

        Integer relativeId = null;
        int userId = 1;


//            int userId = (int) request.getSession().getAttribute("userId");

            if (bookingType.equals("relative")) {
            // Handle data for a relative
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String dateOfBirth = request.getParameter("birthdate");
            String city = request.getParameter("city");
            String postalCode = request.getParameter("postalcode");
//            Date dateOfBirth = null;
//            try {
//                dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirthStr);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            // Create and insert the relative record
            Relative relative = new Relative(userId, firstname, lastname,dateOfBirth, city, postalCode);
            RelativeDao relativeDAO = new RelativeDao();

            relativeId = relativeDAO.insertRelative(relative);

//                String location = request.getParameter("location");
//                String vaccine = request.getParameter("vaccine");
//                String dateStr = request.getParameter("date");
//                String timeStr = request.getParameter("time");
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Time time = null;
//        try {
//            time = Time.valueOf(timeStr + ":00");
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }


        }
        System.out.println(relativeId);
        System.out.println(userId);
        String location = request.getParameter("location");
        String vaccine = request.getParameter("vaccine");
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
                Appointment appointment = new Appointment(userId, relativeId, location, vaccine, dateStr, timeStr);

                AppointmentDao appointmentDAO = new AppointmentDao();

              int appointmentId =  appointmentDAO.addAppointment(appointment);

//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Time time = null;
//        try {
//            time = Time.valueOf(timeStr + ":00");
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }


//        Appointment appointment = new Appointment(userId, location, vaccine, dateStr, timeStr);
//
//        AppointmentDao appointmentDAO = new AppointmentDao();
//
//        appointmentDAO.addAppointment(appointment);

        // Redirect or provide some response to the client
        response.sendRedirect("confirmation.html");
    }
}



