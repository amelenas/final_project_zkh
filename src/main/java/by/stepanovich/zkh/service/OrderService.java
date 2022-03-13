package by.stepanovich.zkh.service;

import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OrderService {
   Order registerOrder(Integer userId, String street, String houseNumber, String apartment, String scopeOfWork, String desirableTimeOfWork, String photo) throws ServiceException;

    Order findById(long id) throws ServiceException;

    List<Order> findAllUserOrderById(long userId) throws ServiceException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus) throws ServiceException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus, int page) throws ServiceException;

    Map<String, String> extractPhotos() throws ServiceException;

    Set<Order> findAllOrders() throws ServiceException;

    boolean updateOrderStatus(long orderId, OrderStatus orderStatus) throws ServiceException;


}
