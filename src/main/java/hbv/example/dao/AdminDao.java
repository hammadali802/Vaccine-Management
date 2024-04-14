package hbv.example.dao;


import hbv.example.db.DBUtil;
import hbv.example.model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public static final String INSERT_INTO_TIMESLOTS_NAME_LOCATION_VALUES = "INSERT INTO timeslots  (`start_time`, `end_time`, `center_id`, `capacity`) VALUES(?, ?, ?,?)";
    Admin admin = null;

    public Admin isValidAdmin(String email, String password) {
        String query = "SELECT * FROM admin WHERE email = ? AND password = ?";

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Admin exists
                    admin = new Admin();

                    admin.setEmail(resultSet.getString("email"));


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
    public  int saveCenter(String name, String location) {

        Connection connection = null;
        int centerId = 0;

        try {

            connection= DBUtil.getConnection();

            System.out.println(connection);

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO vaccinationcenter  (`name`, `location`) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, location);


            preparedStatement1.executeUpdate();

            ResultSet rs = preparedStatement1.getGeneratedKeys();

            if (rs.next()) {
                centerId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return centerId;
    }
    public int saveTimeSlot(String startTime, String endTime, int id,  int capaciy) {

        Connection connection = null;
        int timeSlotId = 0;

        try {

            connection= DBUtil.getConnection();

            System.out.println(connection);

            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_INTO_TIMESLOTS_NAME_LOCATION_VALUES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, startTime);
            preparedStatement1.setString(2, endTime);
            preparedStatement1.setInt(3, id);
            preparedStatement1.setInt(4, capaciy);


            preparedStatement1.executeUpdate();

            ResultSet rs = preparedStatement1.getGeneratedKeys();

            if (rs.next()) {
                timeSlotId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return timeSlotId;
    }
    public  int saveVaccine(int center_id, String name, String manufacturer, int quantity) {

        Connection connection = null;
        int vacId = 0;

        try {

            connection= DBUtil.getConnection();

            System.out.println(connection);

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO vaccines  (`center_id`, `name`, `manufacturer`,`quantity`) VALUES(?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setInt(1, center_id);
            preparedStatement1.setString(2, name);
            preparedStatement1.setString(3, manufacturer);
            preparedStatement1.setInt(4, quantity);


            preparedStatement1.executeUpdate();

            ResultSet rs = preparedStatement1.getGeneratedKeys();

            if (rs.next()) {
                vacId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return vacId;
    }




}
