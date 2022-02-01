package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.impl.OrderDaoImpl;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String apartment,
                                         String scopeOfWork, String desirableTimeOfWork, String photo) throws ServiceException {
        Optional<Order> order;
        if (scopeOfWork == null) {
            throw new ServiceException("The order text is empty");
        }
        try {
            order = orderDao.registerOrder(userId, street, houseNumber, apartment, scopeOfWork, desirableTimeOfWork, photo);
        } catch (DaoException e) {
            throw new ServiceException("Exception when registering an order", e);
        }
        return order;
    }

    @Override
    public Optional<Order> findById(long id) throws ServiceException {
        Optional<Order> order;
        try {
            order = orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching order by OrderID " + id, e);
        }
        return order;
    }

    @Override
    public List<Order> findAllUsersOrderById(long userId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllUsersOrderById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching orders by UserID " + userId, e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllNewOrders() throws ServiceException {
        List<Order> result;
        try {
            result = orderDao.findAllNewOrders();
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching all new orders ", e);
        }
        return result;
    }

    @Override
    public boolean cancelSingleOrder(long orderId) throws ServiceException {
        try {
            return orderDao.cancelSingleOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception when canceling one order by order ID " + orderId, e);
        }
    }

    @Override
    public Map<String, String> extractPhotos() throws ServiceException {
        try {
            return orderDao.extractPhotos();
        } catch (DaoException e) {
            throw new ServiceException("Exception when extracting photos ", e);
        }
    }

    @Override
    public Set<Order> findAllOrders() throws ServiceException {
        try {
            return orderDao.findAllOrder();
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching orders ", e);
        }
    }
}
