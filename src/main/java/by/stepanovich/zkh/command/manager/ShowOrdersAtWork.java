package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShowOrdersAtWork implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowOrdersAtWork.class);
    private WorkService workService = ServiceFactory.getInstance().getWorkService();
    private OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String WORKERS_DATA = "workersData";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        List<Order> orders;
        Map<Order, User> workers = new TreeMap<>();
        try {

            orders = orderService.findOrdersByStatus(OrderStatus.AT_WORK);
            for (Order order : orders){
                List<Long> performersID = workService.findWorkPerformerId(order.getRegistrationId());
                for (Long id: performersID)
                workers.put(order, userService.findById(id).get());
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception when checking applications at work", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setAttribute(WORKERS_DATA, workers);
        return new ResponseContext(PathOfJsp.SHOW_ORDERS_AT_WORK, ResponseContext.ResponseContextType.FORWARD);
    }
}
