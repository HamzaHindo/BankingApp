package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/devdb";

    private static final String USER = "hamza";

    // Use your PostgreSQL password.
    private static final String PASSWORD =
            System.getenv("DB_PASS");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
