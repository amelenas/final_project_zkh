package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllOrderByUserIdCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllOrderByUserIdCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private static final String USER_ID = "id";
    private static final String ORDER_DATA = "orderData";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID);
        try {
            List<Order> orders = orderService.findAllUserOrderById(userId);
            request.setAttribute(ORDER_DATA, orders);
            return new ResponseContext(PathOfJsp.ALL_USER_ORDERS_VIEW, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching user orders ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }
}