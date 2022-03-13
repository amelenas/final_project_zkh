package by.stepanovich.zkh.dao;

import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao {
    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User> registerUser(String email, String password, String userName,
                                String userSurname, String phone) throws DaoException;

    Optional<User> findById(long id) throws DaoException;

    Set<User> findAllUsers() throws DaoException;

    Set<User> findAllUsers(int page) throws DaoException;

    boolean changePassword(String newPassword, long id) throws DaoException;

    Optional<User> updateUserData(long id, String firstName, String lastName, String email, String phone) throws DaoException;

    boolean changeRole(String email, Role role) throws DaoException;
}
