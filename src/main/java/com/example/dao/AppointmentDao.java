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
import com.example.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDao {

    public int addAppointment(Appointment appointment) {
        String query;
        if (appointment.getRelativeId() != null) {
            query = "INSERT INTO appointments (user_id, relative_id, vaccination_centre, vaccine, date, time) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            query = "INSERT INTO appointments (user_id, vaccination_centre, vaccine, date, time) VALUES (?, ?, ?, ?, ?)";
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
}
