package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private static final String REGISTER_ITEM_SQL = "INSERT INTO orders (user_id, street, house_number, scope_of_work," +
            "desirable_time_of_work, opening_date, closing_date, order_status, additional_information, picture) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
    private static final String FIND_USER_BY_ID_WORK_OPEN_DATE_SQL = "SELECT * FROM orders where (user_id=?  and opening_date=?)";

    @Override
    public Optional<Order> registerOrder(int userId, String street, String houseNumber, String scopeOfWork, String desirableTimeOfWork, String photo) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        try (Connection connection = getInstance().retrieveConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, street);
            preparedStatement.setString(3, houseNumber);
            preparedStatement.setString(4, scopeOfWork);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(desirableTimeOfWork));
            preparedStatement.setTimestamp(6, currentTime);
            preparedStatement.setTimestamp(7, null);
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9, null);
            preparedStatement.setString(10, photo);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException sql) {
            LOGGER.error(sql);
            return Optional.empty();
        }
        return findByIdScopeOfWorkDate(userId, currentTime);
    }

    public Optional<Order> findByIdScopeOfWorkDate(int userId, Timestamp opening_date) {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_WORK_OPEN_DATE_SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setTimestamp(2, opening_date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = readOrder(resultSet);
                System.out.println(order);
                return Optional.of(order);
            }
        } catch (SQLException | ConnectionPoolException sql) {
            LOGGER.error(sql);
        }
        return Optional.empty();
    }

    private Order readOrder(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getTimestamp(6),
                resultSet.getTimestamp(7),
                resultSet.getTimestamp(8),
                resultSet.getInt(9),
                resultSet.getString(10),
                resultSet.getString(11),
                resultSet.getBoolean(12),
                resultSet.getInt(13));
    }
}
