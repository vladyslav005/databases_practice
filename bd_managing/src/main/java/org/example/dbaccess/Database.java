package org.example.dbaccess;

import org.example.props.PropertyReader;

import java.sql.*;

public class Database {

    private Connection connection = null;
    private static final Database instance = new Database();
    private Database() {getConnection();}

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection()  {
        if (connection == null) {

            try {
                connection = DriverManager.getConnection(
                        PropertyReader.getDbURL(), PropertyReader.getBdUsername(), PropertyReader.getBdUPassword());
            } catch (SQLException e) {
                System.out.println("Connection failed");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public Statement createStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Unable to create statement");
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String query) {
        try(Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public ResultSet executeResult(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
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

    public void testQuery() {
        String query = "select * from worker order by id asc";

        try (Statement statement = getConnection().createStatement();){
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println( resultSet.getInt("id") + " " + resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}
