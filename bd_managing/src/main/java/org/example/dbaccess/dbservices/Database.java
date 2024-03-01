package org.example.dbaccess.dbservices;

import org.example.props.PropertyReader;

import java.io.IOException;
import java.sql.*;
import java.util.logging.*;

public class Database {
    public static Logger LOGGER = Logger.getLogger(Database.class.getName());
    static {
        Handler ch =  new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(ch);

//        try {
//            Handler fh = new FileHandler("logs/db", false);
//            fh.setFormatter(new SimpleFormatter());
//            LOGGER.addHandler(fh);
//        } catch (IOException e) {;
//            throw new RuntimeException(e);
//        }
    }

    private Connection connection = null;
    private static final Database instance = new Database();

    private Database() {
        getConnection();
    }

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {

            try {
                connection = DriverManager.getConnection(
                        PropertyReader.getDbURL(), PropertyReader.getBdUsername(), PropertyReader.getBdUPassword());
            } catch (SQLException e) {
                LOGGER.warning("Connection failed" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public Statement createStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            LOGGER.warning("Unable to create statement");
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warning("Exception. Reason: " + e.getMessage());
            throw new RuntimeException("Can not run query.");
        }
    }

    public ResultSet executeResult(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            LOGGER.warning("Exception. Reason: " + e.getMessage());
            throw new RuntimeException("Can not run query.");
        }
    }

    public void closeConnection() {
        if (connection == null) return;

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
