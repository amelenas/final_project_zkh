package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AcceptEmployeePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AcceptEmployeePageCommand.class);
    private static final String USERS = "users";
    private static final String EXCEPTION = "exception";
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final WorkService workService = ServiceFactory.getInstance().getWorkService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        try {
            Set<Long> employeeId = workService.findAllNewEmployeeId();
            List<User> users = new ArrayList<>();
            for (long id : employeeId) {
                users.add(userService.findById(id));
            }
            request.setAttribute(USERS, users);
        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterCommand", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.ACCEPT_EMPLOYEE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
