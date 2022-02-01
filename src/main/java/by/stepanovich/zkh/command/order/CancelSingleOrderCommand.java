package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class CancelSingleOrderCommand implements Command {
    private static final OrderService ORDER_SERVICE = ServiceFactory.getInstance().getOrderService();
    private static final String ORDER_ID = "registrationId";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        try {
            ORDER_SERVICE.cancelSingleOrder(orderId);
            return new ResponseContext(PathOfJsp.ALL_USER_ORDERS_COMMAND, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }
}

