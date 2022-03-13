package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.entity.Role;
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

public class AddAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddAdminCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final WorkService workService = ServiceFactory.getInstance().getWorkService();
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private static final String EMAIL = "email";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        try {
            User user = userService.findByEmail(request.getParameter(EMAIL));
           if (user.getRole().equals(Role.EMPLOYEE)){
               List<Long> allOrders = workService.findAllOrdersByEmployeeId(user.getUserId());
               for (long orderId:allOrders){
                   orderService.updateOrderStatus(orderId, OrderStatus.IN_PROCESSING);
               }
               workService.cancelAllEmployeeOrder(user.getUserId());
               workService.changeEmployeeStatus(user.getUserId(),false, true);
           }
            userService.changeRole(request.getParameter(EMAIL), Role.ADMIN);

        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterCommand", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);

        }
        return new ResponseContext(PathOfJsp.SHOW_ADD_ADMIN_COMMAND, ResponseContext.ResponseContextType.FORWARD);
        }
    }

