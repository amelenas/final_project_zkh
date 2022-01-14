package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionBuilder;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import static by.stepanovich.zkh.connection.ConnectionPool.getInstance;


public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private static final String REGISTER_ITEM_SQL = "INSERT INTO users (email, password, user_name, surname, phone, user_status, id_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT * FROM users where email = ?";
    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM users where id_user = ?";

    public Optional<User> registerUser(String email, String password, String userName, String userSurname, String phone) {

        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, userSurname);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, 1);
            preparedStatement.setInt(7, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException sql) {
            LOGGER.error(sql);
        }
        return findByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = readUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException sql) {
            LOGGER.error(sql);
        }
        return Optional.empty();
    }

    private User readUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                UserStatus.of(resultSet.getString(7)),
                Role.of(resultSet.getString(8)));
    }
    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = getInstance().retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = readUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

}
