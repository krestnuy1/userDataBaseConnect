package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/?user=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Kat$evich!1324";
    public static void connectToDB() {
        System.out.println("Connecting database...");

        try  {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!connection.isClosed()){
                System.out.printf("Database connected!");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }
}
