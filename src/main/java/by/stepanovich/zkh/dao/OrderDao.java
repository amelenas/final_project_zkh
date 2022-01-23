package by.stepanovich.zkh.dao;


import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDao {

    Optional<Order> registerOrder(int userId, String street, String houseNumber,
                                  String apartment, String scopeOfWork, String desirableTimeOfWork,
                                  String photo) throws DaoException;

    List<Order> findAllUsersOrderById(long userId) throws DaoException;

    boolean cancelSingleOrder(long userId) throws DaoException;

    Map<String, String> extractPhotos() throws DaoException;
}
