//package com.example.dao;
//
//import com.example.db.DBUtil;
//import com.example.model.Appointment;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class AppointmentDao {
//
//    public int addAppointment(Appointment appointment) {
//        String query = "INSERT INTO appointments (user_id, relative_id, vaccination_centre, vaccine, date, time) VALUES (?, ?, ?, ?, ?, ?)";
//        int id = -1;
//        try (
//                Connection connection = DBUtil.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
//        ) {
//            preparedStatement.setInt(1, appointment.getUserId());
//            preparedStatement.setInt(2, appointment.getRelativeId());
//            preparedStatement.setString(3, appointment.getVaccinationCentre());
//            preparedStatement.setString(4, appointment.getVaccine());
//            preparedStatement.setString(5, appointment.getDate());
//            preparedStatement.setString(6, appointment.getTime());
//
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//     public int addUserAppointment(Appointment appointment) {
//        String query = "INSERT INTO appointments (user_id, vaccination_centre, vaccine, date, time) VALUES ( ?, ?, ?, ?, ?)";
//        int id = -1;
//        try (
//                Connection connection = DBUtil.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
//        ) {
//            preparedStatement.setInt(1, appointment.getUserId());
//            preparedStatement.setString(2, appointment.getVaccinationCentre());
//            preparedStatement.setString(3, appointment.getVaccine());
//            preparedStatement.setString(4, appointment.getDate());
//            preparedStatement.setString(5, appointment.getTime());
//
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//}
package hbv.example.dao;

import hbv.example.db.DBUtil;
import hbv.example.db.Queries;
import hbv.example.model.Appointment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDao {

    public int addAppointment(Appointment appointment) {
        String query;
        if (appointment.getRelativeId() != null) {
            query = Queries.RELATIVE_APPOINTMENT;
        } else {
            query = Queries.USER_APPOINTMENT;
        }
        int id = -1;
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, appointment.getUserId());
            if (appointment.getRelativeId() != null) {
                preparedStatement.setInt(2, appointment.getRelativeId());
                preparedStatement.setInt(3, appointment.getTimeslotId());
                preparedStatement.setInt(4, appointment.getVaccineId());
                preparedStatement.setInt(5, appointment.getVaccinationCenterId());
                preparedStatement.setString(6, appointment.getBookingDate());
            } else {
                preparedStatement.setInt(2, appointment.getTimeslotId());
                preparedStatement.setInt(3, appointment.getVaccinationCenterId());
                preparedStatement.setInt(4, appointment.getVaccineId());
                preparedStatement.setString(5, appointment.getBookingDate());
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

//    public static List<Appointment> getAllAppointments(int userId) throws SQLException {
//        List<Appointment> appointments = new ArrayList<>();
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment_view WHERE user_id = ?")) {
//            preparedStatement.setInt(1, userId);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Appointment appointment = new Appointment();
//                    appointment.setId(resultSet.getInt("id"));
//
//                    // Differentiate between user and relative appointments
//                    if (resultSet.getInt("relative_id") != 0) {
//                        // This appointment is for a relative
//                        Relative relative = new Relative();
//                        relative.setFirstName(resultSet.getString("patient_name"));
//                        relative.setLastName(resultSet.getString("patient_lastname"));
//                        appointment.setRelative(relative);
//                    } else {
//                        // This appointment is for a user
//                        User user = new User();
//                        user.setFirstName(resultSet.getString("patient_name"));
//                        user.setLastName(resultSet.getString("patient_lastname"));
//                        appointment.setUser(user);
//                    }
//
//                    // Set vaccination center name
//                    appointment.getVaccinationCenter().setName(resultSet.getString("vaccination_center_name"));
//
//                    // Set vaccine name
//                    appointment.getVaccine().setName(resultSet.getString("vaccine_name"));
//
//
//                    appointment.setBookingDate(resultSet.getString("booking_date"));
//
//                    // Set time slot start and end times
//                    appointment.getTimeslot().setStartTime(resultSet.getString("timeslot_start_time"));
//                    appointment.getTimeslot().setEndTime(resultSet.getString("timeslot_end_time"));
//
//                    appointments.add(appointment);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e; // Re-throwing the exception for handling at a higher level
//        }
//
//        return appointments;
//    }


//
//    public static List<Appointment> getAllAppointments(int userId) throws SQLException {
//
//        List<Appointment> appointments = new ArrayList<>();
//
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_APPOINTMENTS)) {
//
//            preparedStatement.setInt(1, userId); // Assuming your query expects a user_id parameter
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//
//                    Appointment  appointment = new Appointment();
//
//
//                    appointment.setId(resultSet.getInt("id"));
//                    appointment.setFirstname(resultSet.getString("patient_name"));
//                    appointment.setLastname(resultSet.getString("patient_lastname"));
//                    appointment.setVaccinationCentre(resultSet.getString("vaccination_centre"));
//                    appointment.setVaccine(resultSet.getString("vaccine"));
//                    appointment.setDate(resultSet.getString("date"));
//                    appointment.setTime(resultSet.getString("time"));
//
//                    appointments.add(appointment);
//                    // Set first and last name based on retrieved values
//
//                }
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return appointments;
//    }

    public static boolean deleteAppointment(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_APPOINTMENT)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static JSONObject getAllAppointments(int userId) throws SQLException {
        JSONObject appointmentsJson = new JSONObject();
        JSONArray appointmentsArray = new JSONArray();

        try (
                Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM appointment_view WHERE user_id = ?")) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    JSONObject appointment = new JSONObject();
                    appointment.put("id", resultSet.getInt("id"));
                    appointment.put("firstname", resultSet.getString("patient_name"));
                    appointment.put("last", resultSet.getString("patient_lastname"));
                    appointment.put("vaccinationCenterName", resultSet.getString("vaccination_center_name"));

                    // Set vaccine name
                    appointment.put("vaccineName", resultSet.getString("vaccine_name"));

                    appointment.put("bookingDate", resultSet.getString("booking_date"));

                    // Set time slot start and end times
//                    JSONObject timeSlot = new JSONObject();
                    appointment.put("startTime", resultSet.getString("timeslot_start_time"));
                    appointment.put("endTime", resultSet.getString("timeslot_end_time"));


                    appointmentsArray.put(appointment);
                }
        }   appointmentsJson.put("status", 200);
        appointmentsJson.put("appointments", appointmentsArray );
            }

        catch (SQLException e) {
            e.printStackTrace();
            appointmentsJson.put("status", 500);
            throw e;

        }

//        appointmentsJson.put("appointments", appointmentsArray);
        return appointmentsJson;
    }

//    public static JSONObject AppointmentData(int id) throws SQLException {
//
//        JSONObject appointmentsJson = new JSONObject();
//        JSONArray appointmentsArray = new JSONArray();
//        try (Connection connection = DBUtil.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_APPOINTMENT_BY_ID)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    JSONObject appointment = new JSONObject();
//                    appointment.put("id", resultSet.getInt("id"));
//                    appointment.put("firstname", resultSet.getString("patient_name"));
//                    appointment.put("last", resultSet.getString("patient_lastname"));
//                    appointment.put("vaccinationCenterName", resultSet.getString("vaccination_center_name"));
//
//                    // Set vaccine name
//                    appointment.put("vaccineName", resultSet.getString("vaccine_name"));
//
//                    appointment.put("bookingDate", resultSet.getString("booking_date"));
//
//                    // Set time slot start and end times
////                    JSONObject timeSlot = new JSONObject();
//                    appointment.put("startTime", resultSet.getString("timeslot_start_time"));
//                    appointment.put("endTime", resultSet.getString("timeslot_end_time"));
//
//
//                    appointmentsArray.put(appointment);
//                }
//            }   appointmentsJson.put("status", 200);
//            appointmentsJson.put("appointments", appointmentsArray );
//        }
//
//        catch (SQLException e) {
//            e.printStackTrace();
//            appointmentsJson.put("status", 500);
//            throw e;
//
//        }
//
//        return appointmentsJson;
//    }

    public static String AppointmentDataToHtmlTable(int id) throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table border='1'>");
        htmlTable.append("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Vaccination Center</th><th>Vaccine</th><th>Booking Date</th><th>Start Time</th><th>End Time</th></tr>");

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_APPOINTMENT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    htmlTable.append("<tr>");
                    htmlTable.append("<td>").append(resultSet.getInt("id")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("patient_name")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("patient_lastname")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("vaccination_center_name")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("vaccine_name")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("booking_date")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("timeslot_start_time")).append("</td>");
                    htmlTable.append("<td>").append(resultSet.getString("timeslot_end_time")).append("</td>");
                    htmlTable.append("</tr>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
    }
    public static int countAppointmentsByUserId(int userId) throws SQLException {
        int appointmentCount = 0;
//        String query = "SELECT COUNT(*) AS appointment_count FROM appointments WHERE user_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.COUNT_APPOINTMENT)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    appointmentCount = resultSet.getInt("appointment_count");
                }
            }
        }

        return appointmentCount;
    }
}






