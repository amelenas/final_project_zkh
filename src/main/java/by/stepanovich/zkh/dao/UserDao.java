package by.stepanovich.zkh.dao;

import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User> registerUser(String email, String password, String userName,
                                String userSurname, String phone) throws DaoException;

    Optional<User> findById(long id) throws DaoException;
}
