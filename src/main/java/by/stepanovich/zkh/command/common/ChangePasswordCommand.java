package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String REPEAT_PASSWORD = "repeatPassword";
    private static final String RESULT_INFO = "resultInfo";
    private static final String PASSWORD_NOT_EQUAL = "Passwords not equal";
    private static final String CURRENT_PAGE = "current_page";
    private static final String EXCEPTION = "exception";
    private static final String USER_ID = "id";
    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);

        if (!newPassword.equals(repeatPassword)) {
            request.setAttribute(RESULT_INFO, PASSWORD_NOT_EQUAL);
            return new ResponseContext(PathOfJsp.PASSWORD_CHANGE_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        try {
            if (userService.changePassword(oldPassword, newPassword, String.valueOf(session.getAttribute(USER_ID)))) {
                session.setAttribute(CURRENT_PAGE, PathOfJsp.COMMAND_PASSWORD_CHANGED);
                return new ResponseContext(PathOfJsp.COMMAND_PASSWORD_CHANGED, ResponseContext.ResponseContextType.REDIRECT);
            } else {
                request.setAttribute(RESULT_INFO, PASSWORD_NOT_EQUAL);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.PASSWORD_CHANGE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
