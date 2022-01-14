package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.impl.UserDaoImpl;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao USER_DAO = new UserDaoImpl();

    @Override
    public Optional<User> login(String email, String password) {
        final Optional<User> user = USER_DAO.findByEmail(email);
   //добавить хэширование
        final String realPassword = user.get().getPassword();
        if (password.equals(realPassword)) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> register(String email, String password, String userName, String userSurname, String phone) {

        final Optional<User> optionalUser = USER_DAO.findByEmail(email);
        if (optionalUser.isPresent()) {
            return Optional.empty();
        }
        //добавить хэширование
        Optional<User> user = USER_DAO.registerUser(email, password, userName,userSurname,phone);
        return user;
    }
    @Override
    public Optional<User> findById(Integer id) {
        return  USER_DAO.findById(id);
    }

}
