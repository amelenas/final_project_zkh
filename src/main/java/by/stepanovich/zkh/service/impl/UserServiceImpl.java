package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.impl.UserDaoImpl;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.util.HashPassword;
import by.stepanovich.zkh.util.exeption.ZkhUtilException;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao USER_DAO = new UserDaoImpl();

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = USER_DAO.findByEmail(email);
            if (user.isPresent() && new HashPassword().hashPassword(password).equals(user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        } catch (ZkhUtilException | DaoException e) {
            throw new ServiceException("Exception when the user logs in ",e);
        }
    }

    @Override
    public Optional<User> register(String email, String password, String userName, String userSurname, String phone) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = USER_DAO.findByEmail(email);

        if (optionalUser.isPresent()) {
            return Optional.empty();
        }
        Optional<User> user = USER_DAO.registerUser(email, new HashPassword().hashPassword(password), userName, userSurname, phone);
        return user;
        } catch (DaoException | ZkhUtilException e) {
            throw new ServiceException("Exception during user registration ",e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        try {
            return USER_DAO.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in id search",e);
        }
    }

}
