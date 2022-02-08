package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.manager.ShowOrdersAtWork;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelEmployeeOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelEmployeeOrderCommand.class);
    private WorkService workService = ServiceFactory.getInstance().getWorkService();
    private OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ORDER_ID = "registrationId";
    private static final String USER_ID = "id";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EXCEPTION = "exception";
    private static final String ROLE = "role";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        Role currentRole = Role.valueOf(request.getSession().getAttribute(ROLE).toString().toUpperCase());
        HttpSession session = request.getSession();
        long userId;
        if (currentRole.equals(Role.ADMIN)) {
            userId = Long.parseLong(request.getParameter(EMPLOYEE_ID));
        } else {
            userId = Long.parseLong(String.valueOf(session.getAttribute(USER_ID)));
        }
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        try {
            workService.cancelEmployeeOrder(userId, orderId);
            orderService.updateOrderStatus(orderId, OrderStatus.IN_PROCESSING);
        } catch (ServiceException e) {
            LOGGER.error("Exception when updating order data", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        if (currentRole.equals(Role.EMPLOYEE)) {
            return new ShowEmployeePageCommand().execute(request);
        } else {
            return new ShowOrdersAtWork().execute(request);
        }
    }
}
