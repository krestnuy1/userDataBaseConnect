package jm.task.core.jdbc.util;


import org.slf4j.LoggerFactory;

import java.sql.*;


public class Util {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Util.class);
    private static final String URL = "jdbc:mysql://localhost:3306/UsersDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;
    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Успешное подключение к базе данных");
        } catch (SQLException e) {
            logger.warn("Не удалось подключиться к базе данных");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
