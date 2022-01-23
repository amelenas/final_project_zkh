package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;

import java.sql.*;
import java.util.*;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class OrderDaoImpl implements OrderDao {
    private static final String FIND_USER_BY_ID_WORK_OPEN_DATE_SQL = "SELECT * FROM orders where (user_id=?  and opening_date=?)";
    private static final String REGISTER_ITEM_SQL = """
            INSERT INTO orders (user_id, street, house_number, apartment_number, scope_of_work,
            desirable_time_of_work, opening_date, order_status, picture) 
            VALUES (?, ?, ?, ?, ?, ?, ?,?,?)""";
    private static final String FIND_ALL_ORDER_BY_USER_ID = """
            SELECT * FROM orders 
            WHERE user_id = ?""";

    private static final String CANCEL_ITEM_SQL = """
            UPDATE orders 
            SET order_status = ?
            WHERE registration_number_id = ?""";

    private static final String SQL_FIND_PHOTO = """
            SELECT picture, scope_of_work FROM orders
            WHERE NOT picture IN ('null') AND picture is not null
            ORDER BY opening_date
            DESC LIMIT 6""";

    /*"SELECT picture FROM orders WHERE opening_date > UTC_TIMESTAMP() is not null";*/
    @Override
    public Optional<Order> registerOrder(int userId, String street, String houseNumber, String apartment, String scopeOfWork, String desirableTimeOfWork, String photo) throws DaoException {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String currentTime = timestamp.substring(0, timestamp.indexOf(".")); ;
        try (Connection connection = getInstance().retrieveConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, street);
            preparedStatement.setString(3, houseNumber);
            preparedStatement.setString(4, apartment);
            preparedStatement.setString(5, scopeOfWork);
            System.out.println(currentTime +"при записи");
            preparedStatement.setTimestamp(6, Timestamp.valueOf(desirableTimeOfWork));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(currentTime));
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9, photo);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in OrderDaoImpl ", sql);
        }
        System.out.println(currentTime +"при передаче");
        return findByIdScopeOfWorkDate(userId, currentTime);
    }

    @Override
    public List<Order> findAllUsersOrderById(long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDER_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = readOrder(resultSet);
                    orders.add(order);
                }
            } catch (SQLException ex) {
                throw new DaoException("Exception in findAllUsersOrderById method in OrderDao ", ex);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllUsersOrderById method in OrderDao ", e);
        }
        return orders;
    }

    @Override
    public boolean cancelSingleOrder(long orderId) throws DaoException {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ITEM_SQL);
            preparedStatement.setInt(1, 4);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllUsersOrderById method in OrderDao ", e);
        }
    }

    @Override
    public Map<String, String> extractPhotos() throws DaoException {
        Map<String, String> photos = new HashMap();
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PHOTO);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                photos.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in extractPhotos method in OrderDao ", e);
        }
        return photos;
    }

    private Optional<Order> findByIdScopeOfWorkDate(int userId, String opening_date) throws DaoException {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_WORK_OPEN_DATE_SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(opening_date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = readOrder(resultSet);
                return Optional.of(order);
            }
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in OrderDaoImpl ", sql);
        }
        return Optional.empty();
    }

    private Order readOrder(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getTimestamp(7),
                resultSet.getTimestamp(8),
                resultSet.getTimestamp(9),
                OrderStatus.of(resultSet.getString(10)),
                resultSet.getString(11),
                resultSet.getString(12),
                resultSet.getBoolean(13),
                resultSet.getInt(14));
    }

}
