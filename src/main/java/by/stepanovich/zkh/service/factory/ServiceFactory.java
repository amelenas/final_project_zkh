package by.stepanovich.zkh.service.factory;

import by.stepanovich.zkh.dao.OrderDao;
import by.stepanovich.zkh.dao.UserDao;
import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;
import by.stepanovich.zkh.service.impl.UserServiceImpl;
import by.stepanovich.zkh.service.impl.WorkServiceImpl;

public class ServiceFactory {
    private UserService userService = new UserServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private WorkService workService = new WorkServiceImpl();

    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        public static final ServiceFactory HOLDER_INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.HOLDER_INSTANCE;
    }

    public void setUserService(UserDao userDao) {
        this.userService = new UserServiceImpl(userDao);
    }

    public void setOrderService(OrderDao orderDao) {
        this.orderService = new OrderServiceImpl(orderDao);
    }

    public void setWorkService(WorkDao workDao) {
        this.workService = new WorkServiceImpl(workDao);
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public WorkService getWorkService() {
        return workService;
    }

}