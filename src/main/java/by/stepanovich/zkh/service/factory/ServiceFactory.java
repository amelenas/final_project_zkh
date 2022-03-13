package by.stepanovich.zkh.service.factory;

import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;
import by.stepanovich.zkh.service.impl.UserServiceImpl;
import by.stepanovich.zkh.service.impl.WorkServiceImpl;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final WorkService workService = new WorkServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){return instance;}

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