package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkDAOImplTest {
    private WorkDao workDao = new WorkDAOImpl();

    @Before
    public void initPool() {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllSitesOfWork() {
        try {
            System.out.println(workDao.findAllSitesOfWork());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTypesOfWork() {
        try {
            System.out.println(workDao.findAllTypesOfWork());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findPerformerForWork() {
        try {
            System.out.println(workDao.findPerformerForWork(5,6));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerRunWork() {
        try {
            Assert.assertTrue(workDao.registerRunWork(11,1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllOrdersByEmployeeId() {
        try {
            System.out.println(workDao.findAllOrdersByEmployeeId(11));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelEmployeeOrder() {
        try {
           Assert.assertTrue(workDao.cancelEmployeeOrder(11,1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerEmployee() {
        try {
            System.out.println(workDao.registerEmployee(1,1,11));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllNewEmployeeId() {
        try {
            System.out.println(workDao.findAllNewEmployeeId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeEmployeeStatus() {
        try {
            System.out.println(workDao.changeEmployeeStatus(11, false, true));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}