package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class WorkDAOImplTest {
    private static final String FIND_ALL_SITES_OF_WORK = "SELECT * FROM sites_of_work";
    private static final String FIND_ALL_TYPES_OF_WORK = "SELECT * FROM types_of_works";

    private WorkDao workDao = new WorkDAOImpl();
    List<SiteOfWork> allSitesOfWork = new ArrayList<>();
    List<TypeOfWork> allTypesOfWork = new ArrayList<>();

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

            try (Connection connection = getInstance().retrieveConnection();
                 Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(FIND_ALL_SITES_OF_WORK);
                while (resultSet.next()) {
                    SiteOfWork siteOfWork = new SiteOfWork(resultSet.getLong(1),
                            resultSet.getString(2));
                    allSitesOfWork.add(siteOfWork);
                }
           Assert.assertArrayEquals(workDao.findAllSitesOfWork().toArray(), allSitesOfWork.toArray());
        } catch (DaoException | ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTypesOfWork() {
        try (Connection connection = getInstance().retrieveConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TYPES_OF_WORK);
            while (resultSet.next()) {
                TypeOfWork typeOfWork =  new TypeOfWork(resultSet.getLong(1),
                        resultSet.getString(2));
                allTypesOfWork.add(typeOfWork);
            }
            Assert.assertArrayEquals(allTypesOfWork.toArray(), workDao.findAllTypesOfWork().toArray());
        } catch (DaoException | ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findPerformerForWork() {
        try {
            Object [] expectedId =  new Long[]{9L, 8L, 21L, 16L, 24L, 24L, 24L, 25L, 28L, 28L, 8L};
            Assert.assertArrayEquals(workDao.findPerformerForWork(5, 6).toArray(), expectedId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerRunWork() {
        try {
            Assert.assertTrue(workDao.registerRunWork(11, 1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllOrdersByEmployeeId() {
        try {
            Object [] expectedId =  new Long[]{13L, 20L, 20L, 20L};
            Assert.assertArrayEquals(expectedId, workDao.findAllOrdersByEmployeeId(18).toArray());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelEmployeeOrder() {
        try {
            Assert.assertTrue(workDao.cancelEmployeeOrder(11, 1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerEmployee() {
        try {
            Assert.assertTrue(workDao.registerEmployee(1, 1, 11));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllNewEmployeeId() {
        try {
            Object [] expectedId =  new Long[]{11L};
            Assert.assertArrayEquals(expectedId, workDao.findAllNewEmployeeId().toArray());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeEmployeeStatus() {
        try {
            Assert.assertTrue(workDao.changeEmployeeStatus(11, false, true));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}