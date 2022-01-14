package by.stepanovich.zkh.service;

import by.stepanovich.zkh.entity.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String scopeOfWork, String desirableTimeOfWork, String photo);

    }
