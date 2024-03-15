package hbv.example.dao;

import hbv.example.db.DBUtil;
import hbv.example.db.Queries;
import hbv.example.model.Relative;

import java.sql.*;

public class RelativeDao {
    public int insertRelative(Relative relative) {
        String query = Queries.INSERT_RELATIVE;
        int id = -1;
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, relative.getUserId());
            preparedStatement.setString(2, relative.getFirstName());
            preparedStatement.setString(3, relative.getLastName());


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