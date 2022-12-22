package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Alex", "Smith", (byte) 21);
        userService.saveUser("John", "Brown", (byte) 54);
        userService.saveUser("Kara", "Johnson", (byte) 13);
        userService.saveUser("Bill", "Williams", (byte) 29);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}