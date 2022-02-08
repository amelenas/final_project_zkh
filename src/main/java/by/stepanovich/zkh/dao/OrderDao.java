package by.stepanovich.zkh.dao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface OrderDao {

    Optional<Order> registerOrder(int userId, String street, String houseNumber,
                                  String apartment, String scopeOfWork, String desirableTimeOfWork,
                                  String photo) throws DaoException;

    List<Order> findAllUsersOrderById(long userId) throws DaoException;

    boolean updateOrderStatus(long orderId, OrderStatus orderStatus) throws DaoException;

    Map<String, String> extractPhotos() throws DaoException;

    Set<Order> findAllOrder() throws DaoException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException;

    List<Order> findOrdersByStatus(OrderStatus orderStatus, int page) throws DaoException;

    Optional<Order>findById(long orderID) throws DaoException;

}
