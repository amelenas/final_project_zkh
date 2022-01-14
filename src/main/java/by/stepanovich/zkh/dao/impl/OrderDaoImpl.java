package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;

public class OrderDaoImpl implements OrderDao{
    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    private static final String REGISTER_ITEM_SQL = "INSERT INTO orders (user_id, street, house_number, scope_of_work," +
            "desirable_time_of_work, opening_date, closing_date, order_status, additional_information, picture) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";

    @Override
    public Optional<Order> registerOrder(int userId, String street, String houseNumber, String scopeOfWork, String desirableTimeOfWork, String photo) {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, street);
            preparedStatement.setString(3, houseNumber);
            preparedStatement.setString(4, scopeOfWork);
            preparedStatement.setDate(5, (new java.sql.Date(System.currentTimeMillis())));
            preparedStatement.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(7, null);
            preparedStatement.setInt(8, 1);
            preparedStatement.setString(9, " ");
            preparedStatement.setString(10, " ");
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException sql) {
            LOGGER.error(sql);
            return Optional.empty();

        }
        return Optional.empty();
    }
    private Order readOrder(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getDate(6),
                resultSet.getInt(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getBoolean(10),
                resultSet.getInt(11));
    }
}
