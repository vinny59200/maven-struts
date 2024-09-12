package com.vv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL      = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USER     = "myuser";
    private static final String PASSWORD = "mypassword";

    static {
        try {
            // Load the PostgreSQL JDBC Driver
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            System.out.println( "vv error: " + e.getMessage() );
        }
    }

    // Method to get a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( URL, USER, PASSWORD );
    }

    // Close connection (optional)
    public static void closeConnection( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "vv error: " + e.getMessage() );
            }
        }
    }
}
