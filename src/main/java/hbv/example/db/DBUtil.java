package hbv.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DBUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_db";

    private static final String USER = "your_password";
    private static final String PASSWORD = "your_password";

    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_WAIT_TIME_SECONDS = 5;

    private static final List<Connection> connectionPool = new ArrayList<>(MAX_POOL_SIZE);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                connectionPool.add(connection);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = null;
            synchronized (connectionPool) {
                if (!connectionPool.isEmpty()) {
                    connection = connectionPool.remove(0);
                } else if (connectionPool.size() < MAX_POOL_SIZE) {
                    connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                }
            }

            if (connection == null) {
                throw new RuntimeException("Timeout beim Warten auf eine Datenbankverbindung");
            }

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Abrufen der Datenbankverbindung", e);
        }
    }

    public static void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            if (connectionPool.size() < MAX_POOL_SIZE) {
                connectionPool.add(connection);
            } else {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Freigeben der Datenbankverbindung", e);
        }
    }


//public static void main(String[] args) throws SQLException {
//        Connection con = DBUtil.getConnection();
//    System.out.println(con);
//    }
}