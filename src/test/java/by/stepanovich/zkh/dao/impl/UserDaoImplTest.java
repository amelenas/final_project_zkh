package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerUser() {
        try {
            System.out.println(userDao.registerUser("test@mail.ru", "Aaaaaaaa8", "Антон", "Чистиков", "+35689752465"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByEmail() {
        try {
            System.out.println(userDao.findByEmail("test@mail.ru"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try {
            System.out.println(userDao.findById(1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsers() {
        try {
            System.out.println(userDao.findAllUsers());
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
            System.out.println(userDao.updateUserData(12, "Катя", "Урюпова", "aaaaaa@mail.ru", "+365895645826"));
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