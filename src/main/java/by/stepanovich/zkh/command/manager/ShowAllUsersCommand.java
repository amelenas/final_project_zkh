package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.tablebuild.ReportSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ShowAllUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllUsersCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String EXCEPTION = "exception";
    private static final String USER_BEAN = "userBean";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        ReportSet userSet;
        try {
            userSet = new ReportSet(userService.findAllUsers());
            request.setAttribute(USER_BEAN, userSet);
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching all users ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.BUILD_USER_REPORT, ResponseContext.ResponseContextType.FORWARD);
    }
}
