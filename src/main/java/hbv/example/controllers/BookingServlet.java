package hbv.example.controllers;


import hbv.example.dao.AppointmentDao;
import hbv.example.dao.Counters;
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
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import static com.example.db.DBUtil;

@WebServlet(name = "BookingServlet", value = "/book")
public class BookingServlet extends HttpServlet {
    Jedis jedis = new Jedis("localhost", 6379);
    private Map<String, Integer> vaccineCounts = new HashMap<>();
    private Map<String, Integer> centerCounts = new HashMap<>();
    Counters counter = new Counters();


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
        counter.incrementPageViews("Page_Called","Buchung");
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

//        centerCounts.put(centerName, centerCounts.getOrDefault(centerName, 0) + 1);

        Integer vaccineId = (Integer) new JSONObject(request.getParameter("vaccine")).get("id");
        int vaccine_id = vaccineId.intValue();
        String vaccineName = (String) new JSONObject(request.getParameter("vaccine")).get("name");
//        vaccineCounts.put(vaccineName, vaccineCounts.getOrDefault(vaccineName, 0) + 1);

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
            counter.redisCounter("Total_Appointment_booked");
//            counter.centerCounter(centerName);
//            counter.vacCounter(vaccineName);
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



