package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CheckClosedOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CheckClosedOrdersCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String CLOSED_ORDERS = "closedOrders";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        List<Order> orders;
        Map<Order, User>closedOrders=new TreeMap<>();
        try {
            orders = orderService.findOrdersByStatus(OrderStatus.CLOSED_BY_EMPLOYEE);
            for (Order order: orders){
                closedOrders.put(order,userService.findById(order.getUserId()));
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception when checking closed applications", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setAttribute(CLOSED_ORDERS, closedOrders);
        return new ResponseContext(PathOfJsp.CHECK_CLOSED_BY_EMPLOYEE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
