package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

import java.util.List;

public class ShowAllOrderByUserId implements Command {

    private static final OrderService ORDER_SERVICE = new OrderServiceImpl() ;
    private static final String USER_ID = "id";
    public static final String ORDER_DATA = "orderData";
    public static final String EXCEPTION = "exception";
    @Override
    public ResponseContext execute(RequestContent request) {
        Long userId = (Long) request.getSessionAttribute(USER_ID);
        try {
            List<Order> orders = ORDER_SERVICE.findAllUsersOrderById(userId);
            System.out.println(orders);
            request.setRequestAttribute(ORDER_DATA, orders);
            return new ResponseContext(PathOfJsp.ALL_USER_ORDERS_VIEW, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
           request.setRequestAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
       }
    }
}