package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

public class CancelSingleOrderCommand implements Command {
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();
    private static final String ORDER_ID = "registrationId";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(RequestContent request) {
        long orderId = Long.parseLong(request.getRequestParameter(ORDER_ID)[0]);

        try {
            ORDER_SERVICE.cancelSingleOrder(orderId);
            return new ResponseContext(PathOfJsp.ALL_USER_ORDERS_COMMAND, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
            request.setRequestAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }
}

