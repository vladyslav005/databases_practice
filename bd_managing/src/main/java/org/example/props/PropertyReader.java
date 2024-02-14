package org.example.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getDbURL() {

        Properties prop = getPropSource();

        return new StringBuilder("jdbc:postgresql://")
                .append(prop.getProperty("postgres.db.host"))
                .append(":")
                .append(prop.getProperty("postgres.db.port"))
                .append("/")
                .append(prop.getProperty("postgres.db.database"))
                .append("?currentSchema=public")
                .toString();
    }

    public static String getBdUsername() {
        Properties prop = getPropSource();
        return prop.getProperty("postgres.db.username");
    }

    public static String getBdUPassword() {
        Properties prop = getPropSource();
        return prop.getProperty("postgres.db.password");
    }

    private static Properties getPropSource() {
        Properties prop = null;
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("appliacation.properties")) {
           prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop;
    }
}