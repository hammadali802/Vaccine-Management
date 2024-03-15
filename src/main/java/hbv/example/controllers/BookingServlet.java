package hbv.example.controllers;


import hbv.example.dao.AppointmentDao;
import hbv.example.dao.RelativeDao;
import hbv.example.emailandpdfgenerator.MailSender;
import hbv.example.emailandpdfgenerator.PdfGenerator;
import hbv.example.model.Appointment;
import hbv.example.model.Relative;
import hbv.example.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

//import static com.example.db.DBUtil;

@WebServlet(name = "BookingServlet", value = "/book")
public class BookingServlet extends HttpServlet {


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = null;
        String lastName = null;
        String email = null;

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String bookingType = request.getParameter("bookingType");

        Integer relativeId = null;
        int userId = -1;

        User user = (User) session.getAttribute("user");
//        email = (String) session.getAttribute(email);
        if (user != null) {
            userId = user.getId();
            firstname=user.getFirstName();
            lastName=user.getLastName();
            email= user.getEmail();

        } else {
            System.out.println("id not found");
        }


        if (bookingType.equals("relative")) {
            // Handle data for a relative
            String vorName = request.getParameter("firstname");
            String nachName = request.getParameter("lastname");



            Relative relative = new Relative(userId, vorName, nachName);
            RelativeDao relativeDAO = new RelativeDao();

            relativeId = relativeDAO.insertRelative(relative);

            firstname = vorName;
            lastName = nachName;


        }

        Integer timeSlotId = (Integer) new JSONObject(request.getParameter("timeslots")).get("id");
        int timeSlot_id = timeSlotId.intValue();
        String timeSlot = (String) new JSONObject(request.getParameter("timeslots")).get("name");

        Integer centerIdInteger = (Integer) new JSONObject(request.getParameter("location")).get("id");
        int center_id = centerIdInteger.intValue();
        String centerName = (String) new JSONObject(request.getParameter("location")).get("name");

        Integer vaccineId = (Integer) new JSONObject(request.getParameter("vaccine")).get("id");
        int vaccine_id = vaccineId.intValue();
        String vaccineName = (String) new JSONObject(request.getParameter("vaccine")).get("name");
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(firstname);


        String appointmentDate = request.getParameter("date");

        int count = 0;
        try {
            count = AppointmentDao.countAppointmentsByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Appointment appointment = new Appointment(userId, relativeId, timeSlot_id, vaccine_id, center_id,  appointmentDate);
        AppointmentDao appointmentDAO = new AppointmentDao();
        if ( count >= 4) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("booking.html?error=4");
//            dispatcher.forward(request, response);
            response.sendRedirect("booking.html?error=4");
                return;
            }


        int appointmentId =  appointmentDAO.addAppointment(appointment);

        if (appointmentId > 0){
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = PdfGenerator.sendMailConfirmationWithPdf(firstname,lastName, centerName, vaccineName, appointmentDate, timeSlot);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            MailSender mailSender = new MailSender();
            mailSender.sendPdfEmail(email, firstname, lastName, byteArrayOutputStream, appointmentId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.html");
            dispatcher.forward(request, response);

        }

        else {

            response.sendRedirect("booking.html?error=1");



        }




  }
}



