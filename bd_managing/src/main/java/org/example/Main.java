package org.example;

import org.example.dbaccess.dbservices.Database;
import org.example.dbaccess.dbservices.daos.ClientDao;
import org.example.dbaccess.entities.ClientEntity;
import org.example.props.PropertyReader;
import org.flywaydb.core.Flyway;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().dataSource(PropertyReader.getDbURL(), PropertyReader.getBdUsername(), PropertyReader.getBdUPassword()).load();
        flyway.migrate();

        ClientDao clientDao = new ClientDao();
        Optional<List<ClientEntity>> client = clientDao.listAll();
        if (client.isPresent()) {
            System.out.println(">>>>>>" + client.get());
        } else {
            System.out.println(">>>>>>" +  " not " );
        }
    }
}