package jm.task.core.jdbc.util;


import java.sql.*;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/UsersDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Успешное подключение к базе данных");
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
