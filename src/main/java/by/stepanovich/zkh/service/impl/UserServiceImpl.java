package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.factory.DaoFactory;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.util.FormValidator;
import by.stepanovich.zkh.util.HashPassword;
import by.stepanovich.zkh.util.exeption.ZkhUtilException;

import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final FormValidator validator = FormValidator.getInstance();
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        Optional<User> user;
        if (!validator.checkEmail(email) || !validator.checkPassword(password)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            user = userDao.findByEmail(email);
            if (user.isPresent() && new HashPassword().hashPassword(password).equals(user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        } catch (ZkhUtilException | DaoException e) {
            throw new ServiceException("Exception when the user logs in ", e);
        }
    }

    @Override
    public Optional<User> register(String email, String password, String userName, String userSurname, String phone) throws ServiceException {
        Optional<User> optionalUser;
        if (!validator.checkEmail(email) || !validator.checkPassword(password)
                || !validator.checkFirstName(userName) || !validator.checkLastName(userSurname)
                || !validator.checkPhone(phone)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            optionalUser = userDao.findByEmail(email);

            if (optionalUser.isPresent()) {
                return Optional.empty();
            }
            return userDao.registerUser(email, new HashPassword().hashPassword(password), userName, userSurname, phone);
        } catch (DaoException | ZkhUtilException e) {
            throw new ServiceException("Exception during user registration ", e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in id search method", e);
        }
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String id) throws ServiceException {
        boolean result = false;
        User user;
        if (!validator.checkPassword(newPassword) && !validator.checkPassword(oldPassword)) {
            throw new ServiceException("New Password or Old Password format is invalid");
        }
        try {
            Optional<User> optionalUser = userDao.findById(Long.parseLong(id));
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                if(user.getPassword().equals(new HashPassword().hashPassword(oldPassword))){
                    result = userDao.changePassword(new HashPassword().hashPassword(newPassword), Long.parseLong(id));
                }
            }
        } catch (DaoException | ZkhUtilException e) {
            throw new ServiceException("Exception in changePassword method", e);
        }
        return result;
    }


    @Override
    public Set<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllUsers method", e);
        }
    }

    @Override
    public Set<User> findAllUsers(int page) throws ServiceException {
        try {
            return userDao.findAllUsers(page);
        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllUsers method", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        if (!validator.checkEmail(email)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            return userDao.findByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Exception in findByEmail method");
        }
    }

    @Override
    public Optional<User> updateUserData(long id, String firstName, String lastName, String email, String phone) throws ServiceException {
        if (!validator.checkEmail(email) || !validator.checkFirstName(firstName) ||
                !validator.checkLastName(lastName) || !validator.checkPhone(phone)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            return userDao.updateUserData(id, firstName, lastName, email, phone);
        } catch (DaoException e) {
            throw new ServiceException("Exception in updateUserData method");
        }
    }

    @Override
    public boolean changeRole(String email, Role role) throws ServiceException {
        try {
            return userDao.changeRole(email, role);
        } catch (DaoException e) {
            throw new ServiceException("Exception in updateUserData method");
        }
    }
}
