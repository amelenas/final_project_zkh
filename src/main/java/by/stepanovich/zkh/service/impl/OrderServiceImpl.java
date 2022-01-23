package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.impl.OrderDaoImpl;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
   private static final OrderDao ORDER_DAO = new OrderDaoImpl();

   public Optional<Order> registerOrder(Integer userId, String street, String houseNumber, String apartment, String scopeOfWork, String desirableTimeOfWork, String photo) throws ServiceException {
      try {
         return ORDER_DAO.registerOrder(userId, street, houseNumber, apartment, scopeOfWork, desirableTimeOfWork, photo);
      } catch (DaoException e) {
         throw new ServiceException("Exception when registering an order", e);
      }
   }

   @Override
   public List<Order> findAllUsersOrderById(long userId) throws ServiceException {
      List<Order> orders;
      try {
         orders = ORDER_DAO.findAllUsersOrderById(userId);
      } catch (DaoException e) {
         throw new ServiceException("Exception while searching orders by UserID " + userId, e);
      }
      return orders;
   }

   @Override
   public boolean cancelSingleOrder(long orderId) throws ServiceException {
      try {
         return ORDER_DAO.cancelSingleOrder(orderId);
      } catch (DaoException e) {
         throw new ServiceException("Exception when canceling one order by order ID " + orderId, e);
      }
   }

   @Override
   public Map<String, String> extractPhotos() throws ServiceException {
      try {
         return ORDER_DAO.extractPhotos();
      } catch (DaoException e) {
         throw new ServiceException("Exception when extracting photos ", e);

      }
   }
}
