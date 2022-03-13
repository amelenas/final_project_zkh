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
    private final UserDao userDao = DaoFactory.getInstance().getUserDao();

    public UserServiceImpl() {
    }

    @Override
    public User login(String email, String password) throws ServiceException {
        if (!validator.checkEmail(email) || !validator.checkPassword(password)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent() && user.get().getPassword().equals(new HashPassword().hashPassword(password))) {
                return user.get();
            } else {
                throw new ServiceException("Password is invalid ");
            }
        } catch (DaoException | ZkhUtilException e) {
            throw new ServiceException("Exception when the user logs in ", e);
        }
    }

    @Override
    public User register(String email, String password, String userName, String userSurname, String phone) throws ServiceException {
        if (!validator.checkEmail(email) || !validator.checkPassword(password)
                || !validator.checkFirstName(userName) || !validator.checkLastName(userSurname)
                || !validator.checkPhone(phone)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            Optional<User> optionalUser = userDao.findByEmail(email);
            if (optionalUser.isEmpty()) {
                return userDao.registerUser(email, new HashPassword().hashPassword(password), userName, userSurname, phone).get();
            } else {
                throw new ServiceException("User is already exist ");
            }
        } catch (DaoException | ZkhUtilException e) {
            throw new ServiceException("Exception during user registration ", e);
        }
    }

    @Override
    public User findById(long id) throws ServiceException {
        try {
            Optional <User> userOptional =  userDao.findById(id);
            if (userOptional.isPresent()){
                return userOptional.get();
            }
            else {
                throw new ServiceException("There is no such user");
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception in id search method", e);
        }
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String id) throws ServiceException {
        boolean result = false;
        if (!validator.checkPassword(newPassword) && !validator.checkPassword(oldPassword)) {
            throw new ServiceException("New Password or old password format is invalid");
        }
        try {
            Optional<User> optionalUser = userDao.findById(Long.parseLong(id));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getPassword().equals(new HashPassword().hashPassword(oldPassword))) {
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
    public User findByEmail(String email) throws ServiceException {
        if (!validator.checkEmail(email)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            Optional <User> userOptional = userDao.findByEmail(email);
            if(userOptional.isPresent()){
                return userOptional.get();
            }
            else {
                throw new ServiceException("There is no such user with email: " + email);
            }

        } catch (DaoException e) {
            throw new ServiceException("Exception in findByEmail method");
        }
    }

    @Override
    public User updateUserData(long id, String firstName, String lastName, String email, String phone) throws ServiceException {
        if (!validator.checkEmail(email) || !validator.checkFirstName(firstName) ||
                !validator.checkLastName(lastName) || !validator.checkPhone(phone)) {
            throw new ServiceException("Input data is invalid");
        }
        try {
            return userDao.updateUserData(id, firstName, lastName, email, phone).get();
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
