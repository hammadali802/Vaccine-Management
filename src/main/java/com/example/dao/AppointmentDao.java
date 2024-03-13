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
package com.example.dao;

import com.example.db.DBUtil;
import com.example.db.Queries;
import com.example.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                preparedStatement.setString(3, appointment.getVaccinationCentre());
                preparedStatement.setString(4, appointment.getVaccine());
                preparedStatement.setString(5, appointment.getDate());
                preparedStatement.setString(6, appointment.getTime());
            } else {
                preparedStatement.setString(2, appointment.getVaccinationCentre());
                preparedStatement.setString(3, appointment.getVaccine());
                preparedStatement.setString(4, appointment.getDate());
                preparedStatement.setString(5, appointment.getTime());
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

    public static List<Appointment> getAllAppointments(int userId) throws SQLException {

        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_APPOINTMENTS)) {

            preparedStatement.setInt(1, userId); // Assuming your query expects a user_id parameter

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    Appointment  appointment = new Appointment();


                    appointment.setId(resultSet.getInt("id"));
                    appointment.setFirstname(resultSet.getString("patient_name"));
                    appointment.setLastname(resultSet.getString("patient_lastname"));
                    appointment.setVaccinationCentre(resultSet.getString("vaccination_centre"));
                    appointment.setVaccine(resultSet.getString("vaccine"));
                    appointment.setDate(resultSet.getString("date"));
                    appointment.setTime(resultSet.getString("time"));

                    appointments.add(appointment);
                    // Set first and last name based on retrieved values

                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return appointments;
    }

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
}











