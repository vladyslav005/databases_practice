package org.example.dbaccess.dbservices;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseInitService {
    public static void main(String[] args) throws SQLException {
        InputStream input = DatabaseInitService.class.getClassLoader().getResourceAsStream("migrations/V1__init_database.sql");
        Scanner s = new Scanner(input).useDelimiter(";");
        Statement statement = Database.getInstance().createStatement();

        while (s.hasNext()) {
            String query = s.next();
            statement.addBatch(query);
        }

        statement.executeBatch();
        s.close();
        Database.getInstance().closeConnection();
    }
}
