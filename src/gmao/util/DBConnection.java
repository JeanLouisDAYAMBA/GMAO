package gmao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "GMAO";
    private static final String PASSWORD = "77777777";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Oracle JDBC Driver found.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Oracle JDBC Driver not found", e);
        }

        Connection connection = null;
        try {
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Success connected");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw e;
        }

        return connection;
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
