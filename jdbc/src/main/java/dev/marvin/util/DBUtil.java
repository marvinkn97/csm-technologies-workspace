package dev.marvin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pass@123";


    public static Connection getConnection() {
        if (connection == null) {
            try {
                System.out.println("trying to connect...");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("connected!!");
            } catch (SQLException e) {
                System.out.println("unable to connect to database...");
            }
        }
        return connection;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                connection = null;
                System.out.println("connection closed");
            } catch (SQLException e) {
                System.out.println("unable to close connection");
            }
        }

    }
}
