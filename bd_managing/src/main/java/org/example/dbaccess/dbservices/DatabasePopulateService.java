package org.example.dbaccess.dbservices;

import org.example.dbaccess.entities.Project;
import org.example.dbaccess.entities.Worker;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabasePopulateService {

    private String INSERT_WORKER = "insert into worker (NAME, BIRTHDAY, level, SALARY) values(?, ?, ?, ?);";
    private PreparedStatement insertWorkerStatement;

    private String INSERT_PROJECT = "insert into project (name, CLIENT_ID, start_date, finish_date) values (?, ?, ?, ?);";
    private PreparedStatement insertProjectStatement;

    private String INSERT_CIENT = "insert into client (NAME) values (?);";
    private PreparedStatement insertClientStatement;


    public DatabasePopulateService() {
        try {
            insertWorkerStatement = Database.getInstance().getConnection().prepareStatement(INSERT_WORKER);
            insertProjectStatement = Database.getInstance().getConnection().prepareStatement(INSERT_PROJECT);
            insertClientStatement = Database.getInstance().getConnection().prepareStatement(INSERT_CIENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertWorker(Worker worker) {
        try {
            insertWorkerStatement.setString(1, worker.getName());
            insertWorkerStatement.setDate(2, Date.valueOf(worker.getBirthday()));
            insertWorkerStatement.setString(3, worker.getLevel());
            insertWorkerStatement.setInt(4,worker.getSalary());
            insertWorkerStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertClient(String name) {
        try {
            insertClientStatement.setString(1, name);
            insertClientStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertProject(Project project) {
        try {
            insertProjectStatement.setString(1, project.getName());
            insertProjectStatement.setInt(2, project.getClient_id());
            insertProjectStatement.setDate(3, Date.valueOf(project.getStart_date()));
            insertProjectStatement.setDate(4, Date.valueOf(project.getFinish_date()));

            insertProjectStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


        public static void populateDataBase() throws SQLException {
        InputStream input = DatabasePopulateService.class.getClassLoader().getResourceAsStream("db/migration/V2__populate_db.sql");
        Scanner s = new Scanner(input).useDelimiter(";");

        Statement statement = Database.getInstance().createStatement();

        try {
            while (s.hasNext()) {
                String query = s.next();
                System.out.println(query);

                statement.addBatch(query);
            }

            statement.executeBatch();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        s.close();
        Database.getInstance().closeConnection();
    }
}
