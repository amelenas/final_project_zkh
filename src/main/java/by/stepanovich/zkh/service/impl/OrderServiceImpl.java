package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.factory.DaoFactory;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.util.FormValidator;

import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
    private final FormValidator validator = FormValidator.getInstance();

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String apartment,
                                         String scopeOfWork, String desirableTimeOfWork, String photo) throws ServiceException {
        Optional<Order> order;
        if (scopeOfWork == null || scopeOfWork.equals("")) {
            throw new ServiceException("The order text is empty");
        }
        if(validator.checkXssTag(street) || validator.checkXssTag(houseNumber)
                || validator.checkXssTag(apartment) || validator.checkXssTag(scopeOfWork) || validator.checkXssTag(photo)){
            throw new ServiceException("The order contains < or > symbol");
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
    public List<Order> findAllUserOrderById(long userId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllUsersOrderById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching orders by UserID " + userId, e);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws ServiceException {
        List<Order> result;
        try {
            result = orderDao.findOrdersByStatus(orderStatus);
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching all new orders ", e);
        }
        return result;
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus, int page) throws ServiceException {
        List<Order> result;
        try {
            result = orderDao.findOrdersByStatus(orderStatus, page);
        } catch (DaoException e) {
            throw new ServiceException("Exception while searching all new orders ", e);
        }
        return result;
    }

    @Override
    public boolean updateOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException {
        try {
            return orderDao.updateOrderStatus(orderId, orderStatus);
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
