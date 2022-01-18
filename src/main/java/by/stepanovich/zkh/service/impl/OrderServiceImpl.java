package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.impl.OrderDaoImpl;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {
   private static final OrderDao ORDER_DAO = new OrderDaoImpl();

   public Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String scopeOfWork, String desirableTimeOfWork, String photo){
      return ORDER_DAO.registerOrder(userId, street, houseNumber, scopeOfWork, desirableTimeOfWork, photo);
   }
}
