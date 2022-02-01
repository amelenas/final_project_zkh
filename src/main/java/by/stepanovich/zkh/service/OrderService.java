package by.stepanovich.zkh.service;

import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface OrderService {
    Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String apartment, String scopeOfWork, String desirableTimeOfWork, String photo) throws ServiceException;

    Optional<Order> findById(long id) throws ServiceException;

    List<Order> findAllUsersOrderById(long userId) throws ServiceException;

    List<Order>findAllNewOrders() throws ServiceException;

    boolean cancelSingleOrder(long orderId) throws ServiceException;

    Map<String, String> extractPhotos() throws ServiceException;

    Set<Order> findAllOrders() throws ServiceException;

}
