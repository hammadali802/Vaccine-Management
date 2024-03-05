package com.example.db;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DatabaseConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Retrieve database connection information from context initialization parameters
        String url = context.getInitParameter("dbUrl");
        String username = context.getInitParameter("dbUsername");
        String password = context.getInitParameter("dbPassword");

        // Establish the database connection
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            context.setAttribute("dbConnection", connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup resources, if necessary
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbConnection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
