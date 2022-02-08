package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.entity.User;

import java.sql.*;
import java.util.*;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class OrderDaoImpl implements OrderDao {
    private static final int ELEMENTS_ON_PAGE = 10;
    private static final String FIND_USER_ORDER_BY_ID_WORK_OPEN_DATE = """
            SELECT *
            FROM orders where (user_id=?  and opening_date=?)""";
    private static final String FIND_ORDERS_BY_STATUS = """
            SELECT *
            FROM orders
            WHERE order_status = ?""";
    private static final String FIND_ALL_ORDERS = """
            SELECT *
            FROM orders""";
    private static final String REGISTER_ITEM = """
            INSERT INTO orders (user_id, street, house_number, apartment_number, scope_of_work,
            desirable_time_of_work, opening_date, order_status, picture)
            VALUES (?, ?, ?, ?, ?, ?, ?,?,?)""";
    private static final String FIND_ALL_ORDER_BY_USER_ID = """
            SELECT *
            FROM orders
            WHERE user_id = ?""";
    private static final String FIND_ORDER_BY_ID = """
            SELECT *
            FROM orders
            WHERE registration_number_id = ?""";

    private static final String UPDATE_ORDER_STATUS = """
            UPDATE orders
            SET order_status = ?, closing_date=?
            WHERE registration_number_id = ?""";

    private static final String FIND_PHOTO = """
            SELECT picture, scope_of_work FROM orders
            WHERE NOT picture IN ('null') AND picture is not null AND NOT picture IN ('') AND NOT order_status IN (4)
            ORDER BY opening_date
            DESC LIMIT 6""";
    private static final String FIND_PAGE_QUERY = """
            SELECT *   
            FROM orders  
            WHERE order_status = ?
            LIMIT ?, ?;""";

    @Override
    public Optional<Order> registerOrder(int userId, String street, String houseNumber, String apartment, String scopeOfWork, String desirableTimeOfWork, String photo) throws DaoException {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String currentTime = timestamp.substring(0, timestamp.indexOf("."));
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM)){
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, street);
            preparedStatement.setString(3, houseNumber);
            preparedStatement.setString(4, apartment);
            preparedStatement.setString(5, scopeOfWork);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(desirableTimeOfWork));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(currentTime));
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9, photo);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in OrderDaoImpl ", sql);
        }
         return findByIdScopeOfWorkDate(userId, currentTime);
    }

    @Override
    public List<Order> findAllUsersOrderById(long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDER_BY_USER_ID)){
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
    public boolean updateOrderStatus(long orderId, OrderStatus orderStatus) throws DaoException {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String currentTime = timestamp.substring(0, timestamp.indexOf("."));

        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)){
            preparedStatement.setInt(1, orderStatus.ordinal()+1);
            if(orderStatus.equals(OrderStatus.COMPLETED)){
                preparedStatement.setTimestamp(2, Timestamp.valueOf(currentTime));
            }else {
                preparedStatement.setTimestamp(2, null);
            }
            preparedStatement.setLong(3, orderId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in updateOrderStatus method in OrderDao ", e);
        }
    }

    @Override
    public Map<String, String> extractPhotos() throws DaoException {
        Map<String, String> photos = new HashMap();
        try (Connection connection = getInstance().retrieveConnection();
            Statement statement =  connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(FIND_PHOTO);
            while (resultSet.next()) {
                photos.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in extractPhotos method in OrderDao ", e);
        }
        return photos;
    }

    @Override
    public Set<Order> findAllOrder() throws DaoException {
        Set<Order> orders = new HashSet<>();
        try (Connection connection = getInstance().retrieveConnection();
            Statement statement =  connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ORDERS);
                while (resultSet.next()) {
                    Order order = readOrder(resultSet);
                    orders.add(order);
                }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in findAllUsersOrderById method in OrderDao ", e);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException {
        List <Order> orders = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_STATUS)){
            preparedStatement.setLong(1, orderStatus.ordinal()+1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               orders.add(readOrder(resultSet));
            }
        } catch (SQLException | ConnectionPoolException sql) {
            throw new DaoException("Exception in OrderDaoImpl in findOrdersByStatus method ", sql);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus, int page) throws DaoException {
        List<Order> ordersOnPage = new ArrayList<>();
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            preparedStatement.setInt(1, orderStatus.ordinal()+1);
            preparedStatement.setInt(2, ELEMENTS_ON_PAGE * (page - 1));
            preparedStatement.setInt(3, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = readOrder(resultSet);
                    ordersOnPage.add(order);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("findAll(int page) - Failed to find all users on defined page " + page, e);
        }
        return ordersOnPage;
    }

    @Override
    public Optional<Order> findById(long orderID) throws DaoException {
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID)){
            preparedStatement.setLong(1, orderID);
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

    private Optional<Order> findByIdScopeOfWorkDate(int userId, String opening_date) throws DaoException {
        try (Connection connection = getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ORDER_BY_ID_WORK_OPEN_DATE)){
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
                resultSet.getString(11));
    }
}
