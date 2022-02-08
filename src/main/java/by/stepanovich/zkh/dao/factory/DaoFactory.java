package by.stepanovich.zkh.dao.factory;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.impl.OrderDaoImpl;
import by.stepanovich.zkh.dao.impl.UserDaoImpl;
import by.stepanovich.zkh.dao.impl.WorkDAOImpl;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private UserDao userDao = new UserDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private WorkDao workDao = new WorkDAOImpl();

    public static DaoFactory getInstance(){return instance;}

    private DaoFactory() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public WorkDao getWorkDao() {
        return workDao;
    }

    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }








}