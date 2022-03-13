package by.stepanovich.zkh.dao.impl;

import by.stepanovich.zkh.connection.ConnectionPool;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.entity.UserStatus;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private static final int ELEMENTS_ON_PAGE = 10;
    private static final String REGISTER_ITEM = """
            INSERT INTO users (email, password, user_name, surname, phone, user_status, id_role) 
            VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users where email = ?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users where id_user = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String CHANGE_PASSWORD = """
            UPDATE users
            SET password = ?
            WHERE id_user = ?""";
    private static final String CHANGE_USER_DATA = """
            UPDATE users
            SET user_name = ?, 
                surname = ?,
                email = ?, 
                phone = ?
            WHERE id_user = ?""";
    private static final String CHANGE_USER_ROLE = """
            UPDATE users
            SET id_role = ?
            WHERE email = ?""";
    private static final String FIND_PAGE_QUERY = """
            SELECT *   
            FROM users  
            LIMIT ?, ?;""";


    public Optional<User> registerUser(String email, String password, String userName, String userSurname, String phone) throws DaoException {
        long id = 0;
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, userSurname);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, 1);
            preparedStatement.setInt(7, 1);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during user registration", e);
        }
        return findById(id);
    }

    public Optional<User> findByEmail(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = readUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception when accessing the data source", e);
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
    public Optional<User> findById(long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = readUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception when accessing the data source", e);
        }
        return Optional.empty();
    }

    @Override
    public Set<User> findAllUsers() throws DaoException {
        Set<User> users = new HashSet<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = readUser(resultSet);
                users.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception when accessing the data source", e);
        }
        return users;
    }

    @Override
    public boolean changePassword(String newPassword, long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in changePassword method in OrderDao ", e);
        }
    }

    @Override
    public Optional<User> updateUserData(long id, String firstName, String lastName, String email, String phone) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_DATA)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            return findById(id);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in updateUserData method in OrderDao ", e);
        }
    }

    @Override
    public boolean changeRole(String email, Role role) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_ROLE)) {
            preparedStatement.setInt(1, role.ordinal() + 1);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception in changeRole method in OrderDao ", e);
        }
    }

    @Override
    public Set<User> findAllUsers(int page) throws DaoException {
        Set<User> usersOnPage = new TreeSet<>();
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PAGE_QUERY)) {
            preparedStatement.setInt(1, ELEMENTS_ON_PAGE * (page - 1));
            preparedStatement.setInt(2, ELEMENTS_ON_PAGE);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = readUser(resultSet);
                    usersOnPage.add(user);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("findAll(int page) - Failed to find all users on defined page " + page, e);
        }
        return usersOnPage;
    }

}
