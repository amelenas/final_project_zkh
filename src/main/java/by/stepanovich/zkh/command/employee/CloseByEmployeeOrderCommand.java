package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CloseByEmployeeOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CloseByEmployeeOrderCommand.class);
    private WorkService workService = ServiceFactory.getInstance().getWorkService();
    private OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ORDER_ID = "registrationId";
    private static final String USER_ID = "id";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        long userId = Long.parseLong(String.valueOf(session.getAttribute(USER_ID)));
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        try {
            workService.cancelEmployeeOrder(userId,orderId);
            orderService.updateOrderStatus(orderId, OrderStatus.CLOSED_BY_EMPLOYEE);
        } catch (ServiceException e) {
            LOGGER.error("Exception while updating order data", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ShowEmployeePageCommand().execute(request);
    }
}
