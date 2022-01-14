package by.stepanovich.zkh.dao;

import by.stepanovich.zkh.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    Optional<User> registerUser(String email, String password, String userName, String userSurname, String phone);
    Optional<User> findById(int id);
}
