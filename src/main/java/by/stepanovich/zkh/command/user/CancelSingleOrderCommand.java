package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelSingleOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelSingleOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private static final String ORDER_ID = "registrationId";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        try {
            orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
            return new ResponseContext(PathOfJsp.ALL_USER_ORDERS_COMMAND, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
            LOGGER.error("Exception when updating order status ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }
}

