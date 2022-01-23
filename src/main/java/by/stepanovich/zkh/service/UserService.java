package by.stepanovich.zkh.service;


import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.entity.UserStatus;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;

    Optional<User> register(String email, String password, String userName, String userSurname, String phone) throws ServiceException;

    Optional<User> findById(long id) throws ServiceException;


}
