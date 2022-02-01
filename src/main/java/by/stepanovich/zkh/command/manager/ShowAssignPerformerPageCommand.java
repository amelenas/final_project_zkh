package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAssignPerformerPageCommand implements Command {
    private static final OrderService ORDER_SERVICE = ServiceFactory.getInstance().getOrderService();
    public static final String ORDER_DATA = "orderData";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        List<Order> newOrders;
        try {
            newOrders = ORDER_SERVICE.findAllNewOrders();
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setAttribute(ORDER_DATA, newOrders);

        return new ResponseContext(PathOfJsp.ASSIGN_PERFORMER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
