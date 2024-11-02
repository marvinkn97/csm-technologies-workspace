package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static  Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pos";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pass@123";

    public static Connection getConnection(){
        try {
            if(connection != null){
                return connection;
            }
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unexpected error occurred " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
