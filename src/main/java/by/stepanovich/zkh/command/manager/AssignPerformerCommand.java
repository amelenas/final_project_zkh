package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
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
import java.util.Optional;

public class AssignPerformerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AssignPerformerCommand.class);

    private OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private WorkService workService = ServiceFactory.getInstance().getWorkService();

    private static final String USER_ID = "userId";
    private static final String REGISTRATION_ID = "registrationId";
    private static final String NAME = "name";
    private static final String SURNAME = "userSurname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ORDER_DATA = "orderData";
    private static final String SITES_OF_WORK = "sitesOfWork";
    private static final String TYPES_OF_WORKS = "typesOfWorks";
    private static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(REGISTRATION_ID));
       User user;
        Order order;
        List<SiteOfWork> sitesOfWork;
        List<TypeOfWork> typesOfWorks;
        try {
            sitesOfWork = workService.findAllSitesOfWork();
            typesOfWorks = workService.findAllTypesOfWork();
            order = orderService.findById(orderId);
            user = userService.findById(order.getUserId());

            request.setAttribute(USER_ID, user.getUserId());
            request.setAttribute(NAME, user.getUserName());
            request.setAttribute(SURNAME, user.getUserSurname());
            request.setAttribute(EMAIL, user.getEmail());
            request.setAttribute(PHONE, user.getPhone());
            request.setAttribute(ORDER_DATA, order);
            request.setAttribute(SITES_OF_WORK, sitesOfWork);
            request.setAttribute(TYPES_OF_WORKS, typesOfWorks);
            request.setAttribute(REGISTRATION_ID, orderId);

        } catch (ServiceException e) {
            LOGGER.error("Exception when assign performer", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.ASSIGN_PERFORMER, ResponseContext.ResponseContextType.FORWARD);
    }
}
