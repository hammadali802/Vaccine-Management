package com.example.dao;

import com.example.db.DBUtil;
import com.example.model.Relative;
import com.example.model.User;

import java.sql.*;

public class RelativeDao {
    public int insertRelative(Relative relative) {
        String query = "INSERT INTO relatives (user_id, firstname, lastname, dateofbirth, city, postalcode) VALUES (?, ?, ?, ?, ?, ?)";
        int id = -1;
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, relative.getUserId());
            preparedStatement.setString(2, relative.getFirstname());
            preparedStatement.setString(3, relative.getLastname());
            preparedStatement.setString(4, relative.getDateOfBirth());
            preparedStatement.setString(5, relative.getCity());
            preparedStatement.setString(6, relative.getPostalCode());

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