package org.example.dbaccess.dbservices;

import org.example.dbaccess.Database;
import org.example.dbaccess.data.MaxProjectCountClient;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseQueryService {

    public static void main(String[] args) throws SQLException {
        DatabaseQueryService db = new DatabaseQueryService();
        System.out.println(db.findMaxProjectsClient().toString());
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        try (Statement st = Database.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(getQueryFromFile("sql/find_max_projects_client.sql"));
            List<MaxProjectCountClient> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new MaxProjectCountClient(
                        rs.getInt("client_id"), rs.getString("client_name"), rs.getInt("count_of_entries")));
            }
            rs.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String getQueryFromFile(String filename) {
        InputStream input = DatabaseInitService.class.getClassLoader().getResourceAsStream(filename);
        Scanner s = new Scanner(input).useDelimiter("\\A");
        String query = s.hasNext() ? s.next() : "";
        s.close();
        return query;
    }

}
