package org.example.dbaccess.dbservices.daos;

import org.example.dbaccess.dbservices.Database;
import org.example.dbaccess.entities.ClientEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao {

    private String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE id = (?);";
    private String INSERT_CLIENT = "INSERT INTO client (name) values (?);";
    private String GET_LAST_ADDED = "SELECT * FROM client WHERE id IN (SELECT max(id) FROM client);";

    private String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = (?)";
    private String SET_NAME = "UPDATE client SET name = (?) WHERE id = (?);";
    private String GET_ALL = "SELECT * FROM client;";


    private PreparedStatement getAllStatement;

    private PreparedStatement setNameStatement;
    private PreparedStatement getClientByIdStatement;
    private PreparedStatement getLastAdded;
    private PreparedStatement deleteClient;
    private PreparedStatement insertClient;

    public ClientDao() {
        try {
            getClientByIdStatement = Database.getInstance().getConnection().prepareStatement(GET_CLIENT_BY_ID);
            insertClient = Database.getInstance().getConnection().prepareStatement(INSERT_CLIENT);
            getLastAdded = Database.getInstance().getConnection().prepareStatement(GET_LAST_ADDED);
            deleteClient = Database.getInstance().getConnection().prepareStatement(DELETE_CLIENT_BY_ID);
            setNameStatement = Database.getInstance().getConnection().prepareStatement(SET_NAME);
            getAllStatement = Database.getInstance().getConnection().prepareStatement(GET_ALL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<ClientEntity>> listAll() {
        try {
            List<ClientEntity> clientEntityList= new ArrayList<>();
            ResultSet resultSet = getAllStatement.executeQuery();

            while (resultSet.next()) {
                ClientEntity client = new ClientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
                clientEntityList.add(client);
            }

            return Optional.of(clientEntityList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name) {
        try {
            setNameStatement.setString(1, name);
            setNameStatement.setLong(2, id);

            setNameStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteClientById(long id) {
        try {
            deleteClient.setLong(1, id);

            deleteClient.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long insertClient(String name) {
        try {
            if (name.length() > 20) throw new SQLException("Name is too long");

            insertClient.setString(1, name);


            insertClient.executeUpdate();

            Optional<ClientEntity> lastAdded = getLastAdded();

            if (lastAdded.isEmpty() || !lastAdded.get().getName().equals(name)) {
                throw new SQLException("Wasn't added");
            } else return lastAdded.get().getId();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<ClientEntity> getClientById(int id) {
        try {
            getClientByIdStatement.setInt(1, id);
            ResultSet resultSet = getClientByIdStatement.executeQuery();
            if (resultSet.next()) {
                ClientEntity client = new ClientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));

                return Optional.of(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public Optional<ClientEntity> getLastAdded() {
        try {
            ResultSet resultSet = getLastAdded.executeQuery();
            if (resultSet.next()) {
                ClientEntity client = new ClientEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));

                return Optional.of(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
