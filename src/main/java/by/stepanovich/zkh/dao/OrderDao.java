package by.stepanovich.zkh.dao;


import by.stepanovich.zkh.entity.Order;

import java.util.Optional;

public interface OrderDao {
    Optional<Order> registerOrder(int userId, String street, String houseNumber, String scopeOfWork, String desirableTimeOfWork, String photo);

}
