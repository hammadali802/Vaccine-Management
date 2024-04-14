package hbv.example.dao;

import hbv.example.db.DBUtil;
import hbv.example.db.Queries;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class BookingDao {


        public static JSONObject getAllCentersAsJSON() throws SQLException {

            JSONObject response = new JSONObject();
            JSONArray data = new JSONArray();

            try (Connection connection = DBUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_CENTERS)
            ) {

//            preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JSONObject center = new JSONObject();
                        center.put("id", resultSet.getInt("id"));
                        center.put("name", resultSet.getString("name"));
                     center.put("location", resultSet.getString("location"));


                        data.put( center);
                    }
                }
                response.put("status", 200);
                response.put("data", data);
            } catch (SQLException e) {
                e.printStackTrace();
                response.put("status", 500);
            }
            System.out.println(response.toString());

            return response;
        }
        public static JSONObject getAllVaccinesAsJSON(int id) throws SQLException {

            JSONObject response = new JSONObject();
            JSONArray data = new JSONArray();

            try (Connection connection = DBUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_VACCINE_BY_ID)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        JSONObject vaccine = new JSONObject();
                        vaccine.put("id", resultSet.getInt("id"));
                        vaccine.put("center_id", resultSet.getInt("center_id"));
                        vaccine.put("name", resultSet.getString("name"));
                        vaccine.put("manufacturer", resultSet.getString("manufacturer"));
                        vaccine.put("quantity", resultSet.getInt("quantity"));
//                    vaccine.put("location", resultSet.getString("location"));


                        data.put(vaccine);
                    }
                }
                response.put("status", 200);
                response.put("data", data);
            } catch (SQLException e) {
                e.printStackTrace();
                response.put("status", 500);
            }
            System.out.println(response);
            return response;
        }



    public static JSONObject getAllTimeSlots(int id) throws SQLException {

        JSONObject response = new JSONObject();
        JSONArray data = new JSONArray();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_TIMESLOT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    JSONObject timeSlot = new JSONObject();
                    timeSlot.put("id", resultSet.getInt("id"));
                    timeSlot.put("center_id", resultSet.getInt("center_id"));
                    timeSlot.put("start_time", resultSet.getString("start_time"));
                    timeSlot.put("end_time", resultSet.getString("end_time"));
                    timeSlot.put("capacity", resultSet.getInt("capacity"));
//                    vaccine.put("location", resultSet.getString("location"));



                    data.put(timeSlot);
                }
            }
            response.put("status", 200);
            response.put("data", data);
        } catch (SQLException e) {
            e.printStackTrace();
            response.put("status", 500);
        }
        System.out.println(response);
        return response;
    }

//        public static JSONObject getAllTimeSlots(int id) throws SQLException {
//
//            JSONObject response = new JSONObject();
//            JSONArray data = new JSONArray();
//
//            try (Connection connection = DBUtil.getConnection();
//                 PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_TIMESLOT_BY_ID)) {
//                preparedStatement.setInt(1, id);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//
//                        JSONObject timeSlot = new JSONObject();
//                        timeSlot.put("id", resultSet.getInt("id"));
//                        timeSlot.put("center_id", resultSet.getInt("center_id"));
//                        timeSlot.put("timeSlot", resultSet.getString("start_time"));
////                    vaccine.put("location", resultSet.getString("location"));
//
//
//
//                        data.put(timeSlot);
//                    }
//                }
//                response.put("status", 200);
//                response.put("data", data);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                response.put("status", 500);
//            }
//            System.out.println(response);
//            return response;
//        }

    }

