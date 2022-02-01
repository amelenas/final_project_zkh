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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AssignPerformerCommand implements Command {
    private OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private WorkService workService = ServiceFactory.getInstance().getWorkService();

    public static final String USER_ID = "userId";
    public static final String REGISTRATION_ID = "registrationId";
    public static final String ORDER = "order";
    private static final String NAME = "name";
    private static final String SURNAME = "userSurname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ORDER_DATA = "orderData";
    private static final String SITES_OF_WORK = "sitesOfWork";
    private static final String TYPES_OF_WORKS = "typesOfWorks";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(REGISTRATION_ID));
        Optional<User> userOptional;
        Optional<Order> orderOptional;
        List<SiteOfWork> sitesOfWork;
        List<TypeOfWork> typesOfWorks;
        try {
            sitesOfWork = workService.findAllSitesOfWork();
            typesOfWorks = workService.findAllTypesOfWork();
            orderOptional = orderService.findById(orderId);
            userOptional = userService.findById(orderOptional.get().getUserId());

            request.setAttribute(ORDER, orderOptional.get());
            request.setAttribute(USER_ID, userOptional.get().getUserId());
            request.setAttribute(NAME, userOptional.get().getUserName());
            request.setAttribute(SURNAME, userOptional.get().getUserSurname());
            request.setAttribute(EMAIL, userOptional.get().getEmail());
            request.setAttribute(PHONE, userOptional.get().getPhone());
            request.setAttribute(ORDER_DATA, orderOptional.get());
            request.setAttribute(SITES_OF_WORK, sitesOfWork);
            request.setAttribute(TYPES_OF_WORKS, typesOfWorks);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.ASSIGN_PERFORMER, ResponseContext.ResponseContextType.FORWARD);
    }
}
