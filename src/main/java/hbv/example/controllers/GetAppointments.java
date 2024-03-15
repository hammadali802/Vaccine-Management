package hbv.example.controllers;





import hbv.example.dao.AppointmentDao;

import hbv.example.model.User;
import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetAppointments", value = "/get")
public class GetAppointments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();


        JSONObject appointmentsJSON = null;
        try {
            appointmentsJSON = AppointmentDao.getAllAppointments(userId);


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
//


        response.setContentType("text/json");

        PrintWriter out = response.getWriter();
        out.println(appointmentsJSON);
        out.flush();
    }
}

//
//
//
//        JSONObject objSend = new JSONObject();
//        JSONArray data = new JSONArray();
//
//        int status = 0;
//
//        try {
//            List<Appointment> appointments = AppointmentDao.getAllAppointments(userId);
//
//            for (Appointment a : appointments){
//                JSONObject app = new JSONObject();
//                app.put("id", a.getId());
//                app.put("firstname", a.getFirstname());
//                app.put("lastname", a.getLastname());
//                app.put("impfort", a.getVaccinationCentre());
//                app.put("impfung",a.getVaccine());
//                app.put("date",a.getDate());
//                app.put("time",a.getTime());
//
//                data.put(app);
//                status = 200;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            status = 500;
//        }
//
//
//
//
//        objSend.put("status", status);
//        objSend.put("data", data);
//
//        System.out.println(objSend.toString());
//
//        response.setContentType("text/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            out.print(objSend);
//        } finally {
//            out.close();
//        }
//
//    }}
//
//
//
//
//
//
