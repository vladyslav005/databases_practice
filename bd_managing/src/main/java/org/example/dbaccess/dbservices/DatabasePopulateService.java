package org.example.dbaccess.dbservices;

import java.io.InputStream;
import java.util.Scanner;

public class DatabasePopulateService {
    public static void main(String[] args) {
        InputStream input = DatabasePopulateService.class.getClassLoader().getResourceAsStream("sql/populate_db.sql");
        Scanner s = new Scanner(input).useDelimiter(";");

        while (s.hasNext()) {
            String query = s.next();
            System.out.println(query);
            Database.getInstance().executeUpdate(query);
        }

        s.close();
        Database.getInstance().closeConnection();
    }
}
