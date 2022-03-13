package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddEmployeeCommand implements Command {
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final WorkService workService = ServiceFactory.getInstance().getWorkService();
    private static final Logger LOGGER = LogManager.getLogger(AddEmployeeCommand.class);

    private static final String EXCEPTION = "exception";
    private static final String EMAIL = "email";
    private static final String USER_ID = "userId";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        long userId = Long.parseLong(String.valueOf(request.getParameter(USER_ID)));
        try {
            userService.changeRole(request.getParameter(EMAIL), Role.EMPLOYEE);
            workService.changeEmployeeStatus(userId, true, false);
        } catch (ServiceException e) {
            LOGGER.error("Exception when adding employee", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.ACCEPT_EMPLOYEE_PAGE_CONTROLLER, ResponseContext.ResponseContextType.FORWARD);
    }
}
