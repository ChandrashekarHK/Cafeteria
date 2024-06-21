package com.cafeteria.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBOperationsHandler {
   private Connection connection;

    public DBOperationsHandler() throws SQLException {
        this.connection = DatabaseConnector.getConnection();
    }


    public ResultSet executeQuery(String query, Object... params) {
        try {
             PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++)
            {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeQuery();
        } catch (SQLException e) {

            return null;
        }
    }

    // Add other database operations as needed (e.g., insert, update, delete)
}
