package jm.task.core.jdbc.dao;

import com.sun.xml.bind.v2.util.StackRecorder;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDaoJDBCImpl.class);

    public void createUsersTable() {
        String query = "CREATE TABLE `UsersDB`.`users` (" +
                "  `ID` INT NOT NULL AUTO_INCREMENT," +
                "  `Name` VARCHAR(45) NOT NULL," +
                "  `LastName` VARCHAR(45) NOT NULL," +
                "  `Age` INT NULL," +
                "  PRIMARY KEY (`ID`));";

        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
            logger.info("Таблица успешно создана. Имя таблицы: users Поля: ID, Name, LastName, Age");

        } catch (SQLException e) {
            logger.warn("Таблица с таким именем уже существует: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE users";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
            logger.info("Таблица с именем: users удалена!");
        } catch (SQLException e) {
            logger.warn("Таблицы с таким именем не существует: " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
       String query = "INSERT INTO users(Name, LastName, Age) VALUES (" + "'"+name+"'," + " '"+lastName+"',"+age +");";
       try {
           Statement statement = util.getConnection().createStatement();
           statement.execute(query);
           logger.info ("Новая запись добавлена в таблицу." +
                   " Имя: " + name +
                   " Фамилия: " + lastName +
                   " Возраст: " + age);
           statement.close();
       } catch (SQLException e) {
           logger.warn("Ошибка при сохранении пользователя " + e.getMessage());

       }

    }

    public void removeUserById(long id) throws SQLException {
        String query ="DELETE FROM users WHERE ID = " + id +";";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(query);
            logger.info("Запись с ID: " + id + " удалена!");
        } catch (SQLException e) {
            logger.warn("Ошибка удаления пользователя " + e.getMessage());
        }

    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * from users;";
        List<User> allUsers = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("ID")); ;
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge((byte) resultSet.getInt("Age")) ;
                allUsers.add(user);
        }
            statement.close();
            logger.info("Список пользователей сформирован!");
        } catch (SQLException e) {
            logger.warn("Ошибка получения перечня пользователей " + e.getMessage());
        }

        return allUsers;
    }

    public void cleanUsersTable() throws SQLException {
        String query = "DELETE FROM users";
        try {
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
            logger.info("Все пользователи удалены из таблицы");
        } catch (SQLException e) {
            logger.warn("Ошибка очистки таблицы" + e.getMessage());
        }

    }
}
