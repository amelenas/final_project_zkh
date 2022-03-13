package by.stepanovich.zkh.command.manager;

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

public class SentOrderToWorkCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SentOrderToWorkCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ORDER_ID = "registrationId";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        try {
            orderService.updateOrderStatus(orderId, OrderStatus.IN_PROCESSING);
        } catch (ServiceException e) {
            LOGGER.error("Exception when updating order status ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new CheckClosedOrdersCommand().execute(request);

    }
}
