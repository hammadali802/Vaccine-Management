package com.example.dao;

import com.example.db.*;
import com.example.model.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

public class UserDao {
    public int addUser(User user) {
        String query = Queries.INSERT_USER;
        int id = -1;
        try (
              Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, user.getFirstname());

            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getDateOfBirth());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getCity());

            preparedStatement.setString(7, user.getPostalCode());

            preparedStatement.executeUpdate();
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getInt(1);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return id;
    }

    public User isValidUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // User exists, populate User object
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstname(resultSet.getString("firstname"));
                    user.setLastname(resultSet.getString("lastname"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean isEmailExists(String email) {
        String query = "SELECT * FROM users WHERE email = ?";

        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();


            return resultSet.next();  // Returns true if a user with the provided email exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
