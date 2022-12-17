package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydb.users"
                    + "(id INT NOT NULL AUTO_INCREMENT," + "name VARCHAR(45)," + "lastName VARCHAR(45),"
                    + "age INT," + "PRIMARY KEY (id))");
            System.out.println("Таблица успешно создана.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при создании таблицы");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS mydb.users");
            System.out.println("Таблица успешно удалена.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при удалении таблицы.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.users(name, lastName, age) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при добавлении параметров пользователя.");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM mydb.users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с id = " + id + " успешно удалён.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при удалении данных о пользователе.");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, lastName, age FROM mydb.users")) {
            ResultSet resultSet = preparedStatement.executeQuery("SELECT id, name, lastName, age FROM mydb.users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                /*preparedStatement.executeUpdate();*/
                allUsers.add(user);
            }
            System.out.println("Все пользователи добавлены в базу данных.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при получении данных о всех пользователях.");
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE mydb.users")) {
            preparedStatement.executeUpdate("TRUNCATE mydb.users");
            System.out.println("Все пользователи удалены из базы данных.");
        } catch (SQLException e) {
            throw new RuntimeException("Исключение при удалении данных о всех пользователях.");
        }
    }
}