package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WorkDAOImpl implements WorkDao {
    private static final String FIND_ALL_SITES_OF_WORK = "SELECT * FROM sites_of_work";
    private static final String FIND_ALL_TYPES_OF_WORK = "SELECT * FROM types_of_works";
    private static final String FIND_ALL_NEW_EMPLOYEE = "SELECT id_user FROM employee_works WHERE is_active=0 AND on_approval=1";
    private static final String FIND_WORK_PERFORMER = "SELECT id_user FROM run_works WHERE registration_order_id=? AND is_in_work=? ";
    private static final String FIND_ALL_EMPLOYEE_WORKS = """
            SELECT * FROM employee_works 
            WHERE (id_type_of_works=? and site_of_work=? and is_active=? and on_approval=?)""";
    private static final String FIND_ALL_WORKS_BY_EMPLOYEE_ID = " SELECT * FROM run_works WHERE (id_user=? and is_in_work=?)";
    private static final String REGISTER_RUN_WORK = """
            INSERT INTO run_works (registration_order_id, id_user, is_in_work)
            VALUES (?, ?, ?)""";
    private static final String UPDATE_EMPLOYEE_STATUS = """
            UPDATE employee_works
            SET is_active = ?, on_approval = ?
            WHERE id_user = ?""";
    private static final String REGISTER_NEW_EMPLOYEE = """
            INSERT INTO employee_works (id_user, id_type_of_works, site_of_work, is_active)
            VALUES (?, ?, ?, ?)""";
    private static final String CANCEL_ORDER = """
            UPDATE run_works
            SET is_in_work = ?
            WHERE id_user = ? and registration_order_id = ?""";
    private static final String CANCEL_ALL_EMPLOYEE_ORDERS = """
            UPDATE run_works
            SET is_in_work = ?
            WHERE id_user = ?""";

    @Override
    public List<SiteOfWork> findAllSitesOfWork() throws DaoException {
        List<SiteOfWork> siteOfWorks = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             Statement statement = connection.createStatement()){
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
        List<TypeOfWork> typeOfWorks = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            Statement statement = connection.createStatement()){
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

    @Override
    public List<Long> findPerformerForWork(long typeOfWorksId, long siteOfWork) throws DaoException {
        List<Long> usersId = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EMPLOYEE_WORKS)){
            preparedStatement.setLong(1, typeOfWorksId);
            preparedStatement.setLong(2, siteOfWork);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersId.add(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception when accessing the data source", e);
        }
        return usersId;
    }

    @Override
    public boolean registerRunWork(long userId, long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_RUN_WORK)){
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, 1);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in registerRunWork method ", sql);
        }
    }

    @Override
    public List<Long> findAllOrdersByEmployeeId(long employeeId) throws DaoException {
        List<Long> ordersId = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_WORKS_BY_EMPLOYEE_ID)){
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ordersId.add(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception when accessing the data source", e);
        }
        return ordersId;
    }

    @Override
    public boolean cancelEmployeeOrder(long userId, long orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ORDER)){
            preparedStatement.setInt(1, 0);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, orderId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in registerRunWork method ", sql);
        }
    }

    @Override
    public boolean cancelAllEmployeeOrder(long EmployeeId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ALL_EMPLOYEE_ORDERS)){
            preparedStatement.setInt(1, 0);
            preparedStatement.setLong(2, EmployeeId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in cancelAllEmployeeOrder method ", sql);
        }
    }

    @Override
    public boolean registerEmployee(long siteOfWorkId, long typeOfWorkId, long employeeId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_NEW_EMPLOYEE)){
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, typeOfWorkId);
            preparedStatement.setLong(3, siteOfWorkId);
            preparedStatement.setLong(4, 0);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in registerEmployee method ", sql);
        }
    }

    @Override
    public Set<Long> findAllNewEmployeeId() throws DaoException {
        Set<Long> employeeId = new TreeSet<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(FIND_ALL_NEW_EMPLOYEE);
            while (resultSet.next()) {
                employeeId.add(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllNewEmployeeId method", e);
        }
        return employeeId;
    }

    @Override
    public List<Long> findWorkPerformerId(long orderId) throws DaoException {
        List<Long> employeeId = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_WORK_PERFORMER)){
            preparedStatement.setLong(1, orderId);
            preparedStatement.setBoolean(2, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeId.add(resultSet.getLong(1));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllNewEmployeeId method", e);
        }
        return employeeId;
    }

    @Override
    public boolean changeEmployeeStatus(long userId, boolean isActive, boolean isOnApproval) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_STATUS)){
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setBoolean(2, isOnApproval);
            preparedStatement.setLong(3, userId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in removeFromEmployees method ", sql);
        }
    }

    private SiteOfWork readSiteOfWork(ResultSet resultSet) throws SQLException {
        return new SiteOfWork(resultSet.getLong(1),
                resultSet.getString(2));
    }

    private TypeOfWork readTypeOfWork(ResultSet resultSet) throws SQLException {
        return new TypeOfWork(resultSet.getLong(1),
                resultSet.getString(2));
    }

}
