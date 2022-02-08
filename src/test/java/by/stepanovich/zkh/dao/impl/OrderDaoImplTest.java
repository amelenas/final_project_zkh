package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
private OrderDao orderDao = new OrderDaoImpl();
    @Before
    public void setUp() throws Exception {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerOrder() {
        try {
            System.out.println(orderDao.registerOrder(6, "Неманская", "56","5","Помогите все сломалось!", "2022-01-30 23:22:00", ""));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsersOrderById() {
        try {
            System.out.println(orderDao.findAllUsersOrderById(2));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateOrderStatus() {
        try {
            Assert.assertTrue(orderDao.updateOrderStatus(2, OrderStatus.AT_WORK));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractPhotos() {
        try {
            System.out.println(orderDao.extractPhotos());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllOrder() {
        try {
            System.out.println(orderDao.findAllOrder());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findOrdersByStatus() {
        try {
            System.out.println(orderDao.findOrdersByStatus(OrderStatus.COMPLETED));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try {
            System.out.println(orderDao.findById(2));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}