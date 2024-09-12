package com.vv.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.Statement;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized( ServletContextEvent sce ) {
        Connection connection = null;
        Statement statement;

        System.out.println( "vv initiating DB details" );

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();

            // Create table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(50) NOT NULL);";
            statement.executeUpdate( createTableSQL );

            System.out.println( "vv table created" );

            // Insert user if not exists
            String insertUserSQL = "INSERT INTO users (username, password) " +
                    "VALUES ('admin', 'admin123') " +
                    "ON CONFLICT (username) DO NOTHING;";
            statement.executeUpdate( insertUserSQL );

            System.out.println( "vv added the user" );

        } catch ( Exception e ) {
            System.out.println( "vv error: " + e.getMessage() );
        } finally {
            DatabaseUtil.closeConnection( connection );
        }
    }

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {
        // Clean up resources here if needed
    }
}

