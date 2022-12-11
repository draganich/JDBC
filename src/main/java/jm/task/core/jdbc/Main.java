package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Alex", "Smith", (byte) 21);
        userDaoJDBC.saveUser("John", "Brown", (byte) 54);
        userDaoJDBC.saveUser("Kara", "Johnson", (byte) 13);
        userDaoJDBC.saveUser("Bill", "Williams", (byte) 29);

        userDaoJDBC.getAllUsers();
        userDaoJDBC.removeUserById(1);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
