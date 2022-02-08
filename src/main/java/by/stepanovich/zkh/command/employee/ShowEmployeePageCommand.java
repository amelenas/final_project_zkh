package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShowEmployeePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowEmployeePageCommand.class);

    private OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private WorkService workService = ServiceFactory.getInstance().getWorkService();
    private static final String USER_ID = "id";
    private static final String ORDERS_BY_EMPLOYEE = "ordersMap";
    private static final String EXCEPTION = "exception";


    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long employeeId = Long.parseLong(String.valueOf(session.getAttribute(USER_ID)));
        List<Long> ordersByEmployeeId;
        Map<Order, User> orders = new TreeMap<>();
        try {
            ordersByEmployeeId = workService.findAllOrdersByEmployeeId(employeeId);
            for (Long orderId : ordersByEmployeeId) {
                Order order = orderService.findById(orderId).get();
                orders.put(order, userService.findById(order.getUserId()).get());
            }
             request.setAttribute(ORDERS_BY_EMPLOYEE, orders);
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching order by id ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.EMPLOYEE_TASK, ResponseContext.ResponseContextType.FORWARD);
    }
}
