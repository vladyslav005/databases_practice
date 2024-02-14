package org.example;

import org.example.dbaccess.Database;
import org.example.props.PropertyReader;

public class Main {
    public static void main(String[] args) {
        Database.getInstance().testQuery();
        Database.getInstance().closeConnection();
    }
}