package hbv.example.controllers;





import java.io.IOException;
import java.io.PrintWriter;


import hbv.example.dao.AppointmentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.*;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONObject objSend = new JSONObject();
        JSONObject data = new JSONObject();

        int status = 200;
        String message = "";

        String id = request.getParameter("id");

        try {
            boolean result = AppointmentDao.deleteAppointment(Integer.parseInt(id));

            if (result) {
                status = 200;
                message = "Erfolgreich Storniert!";
            } else {
                status = 500;
                message = "Etwas schief gelaufen!";
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
            status = 500;
        }

        objSend.put("status", status);
        objSend.put("data", data);
        objSend.put("message", message);

        System.out.println(objSend.toString());

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(objSend);
        } finally {
            out.close();
        }

    }

}
