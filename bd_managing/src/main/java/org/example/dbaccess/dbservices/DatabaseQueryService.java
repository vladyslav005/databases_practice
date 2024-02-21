package org.example.dbaccess.dbservices;

import org.example.dbaccess.entities.LongestProject;
import org.example.dbaccess.entities.MaxProjectCountClient;
import org.example.dbaccess.entities.Worker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.*;

public class DatabaseQueryService {

    private static Logger LOGGER = Logger.getLogger(DatabaseQueryService.class.getName());
    static {

        Handler ch =  new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(ch);

        try {
            Handler fh = new FileHandler("logs/service.log", false);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {;
            throw new RuntimeException(e);
        }
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
            LOGGER.warning(e.getMessage());
            throw new RuntimeException();
        }
    }


    public List<LongestProject> findLongestProject() {
        try (Statement st = Database.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(getQueryFromFile("sql/find_longest_project.sql"));
            List<LongestProject> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new LongestProject(
                        rs.getInt("id"), rs.getString("name"), rs.getInt("date_diff")));
            }
            rs.close();
            return result;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException();
        }
    }


    public List<Worker> findMaxSalaryWorker() {
        try (Statement st = Database.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(getQueryFromFile("sql/find_max_salary_worker.sql"));
            List<Worker> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new Worker(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("level"), rs.getInt("salary")));
            }
            rs.close();
            return result;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException();
        }
    }


    public List<Worker> findOldestAndYoungestWorker() {
        try (Statement st = Database.getInstance().createStatement()) {
            ResultSet rs = st.executeQuery(getQueryFromFile("sql/find_youngest_eldest_workers.sql"));
            List<Worker> result = new ArrayList<>();

            while (rs.next()) {
                result.add(new Worker(
                        rs.getString("type"),
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("level"), rs.getInt("salary")));
            }

            rs.close();
            return result;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException();
        }
    }


    public static String getQueryFromFile(String filename) throws InputMismatchException {
        InputStream input = DatabaseInitService.class.getClassLoader().getResourceAsStream(filename);

        if (input == null) throw new InputMismatchException();

        Scanner s = new Scanner(input).useDelimiter("\\A");
        String query = s.hasNext() ? s.next() : "";
        s.close();
        return query;
    }
}
