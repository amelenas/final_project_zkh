package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class WorkDAOImpl implements WorkDao {
    private static final String FIND_ALL_SITES_OF_WORK = "SELECT * FROM sites_of_work";
    private static final String FIND_ALL_TYPES_OF_WORK = "SELECT * FROM types_of_works";

    @Override
    public List<SiteOfWork> findAllSitesOfWork() throws DaoException {
        List<SiteOfWork>siteOfWorks = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection()) {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SITES_OF_WORK);

            while (resultSet.next()) {
                SiteOfWork siteOfWork = readSiteOfWork(resultSet);
                siteOfWorks.add(siteOfWork);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllSitesOfWork method ", e);
        }
        return siteOfWorks;
    }

    @Override
    public List<TypeOfWork> findAllTypesOfWork() throws DaoException {
        List<TypeOfWork>typeOfWorks = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection()) {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TYPES_OF_WORK);
            while (resultSet.next()) {
                TypeOfWork typeOfWork = readTypeOfWork(resultSet);
                typeOfWorks.add(typeOfWork);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllTypesOfWork method", e);
        }
        return typeOfWorks;
    }

    private SiteOfWork readSiteOfWork(ResultSet resultSet) throws SQLException {
        return new SiteOfWork(resultSet.getLong(1),
                resultSet.getString(2));
    }

    private TypeOfWork readTypeOfWork(ResultSet resultSet) throws SQLException {
        return new TypeOfWork(resultSet.getLong(1),
                resultSet.getString(2));
    }

    public static void main(String[] args) {
        WorkDAOImpl workDAO = new WorkDAOImpl();
        try {
            System.out.println(workDAO.findAllSitesOfWork());
            System.out.println(workDAO.findAllTypesOfWork());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
