package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.entity.UserStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    Set<User> allUsers = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        try (Connection connection = getInstance().retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                User user =  new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        UserStatus.of(resultSet.getString(7)),
                        Role.of(resultSet.getString(8)));
                allUsers.add(user);
            }
        }
    }

    @Test
    public void registerUser() {
        try {
            Assert.assertNotNull(userDao.registerUser("test2@mail.ru", "Aaaaaaaa8", "Антон", "Чистиков", "+35688752465"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByEmail() {
        try {
            User user = new User(14, "test@mail.ru", "Aaaaaaaa8", "Антон", "Чистиков", "+35689752465", UserStatus.VALID, Role.USER);
            Assert.assertEquals(userDao.findByEmail("test@mail.ru").get(), user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try {
            User user = new User(1, "bebe@bebe.be", "123", "Vasya", "Vasiliev", "+654859999235", UserStatus.VALID, Role.USER);
            Assert.assertEquals(userDao.findById(1).get(), user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsers() {
        try {
            Assert.assertArrayEquals(allUsers.toArray(), userDao.findAllUsers().toArray());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changePassword() {
        try {
            Assert.assertTrue(userDao.changePassword("Aaaaaaaaa9", 12));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserData() {
        try {
            User user = new User(12, "aaaaaa@mail.ru", "Aaaaaaaaa9", "Катя", "Урюпова", "+365895645826", UserStatus.VALID, Role.ADMIN);
            Assert.assertEquals(user, userDao.updateUserData(12, "Катя", "Урюпова", "aaaaaa@mail.ru", "+365895645826").get());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeRole() {
        try {
            Assert.assertTrue(userDao.changeRole("aaaaaa@mail.ru", Role.ADMIN));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}