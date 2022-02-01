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
    public static final String OLD_PASSWORD = "oldPassword";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String REPEAT_PASSWORD = "repeatPassword";
    public static final String RESULT_INFO = "resultInfo";
    public static final String PASSWORD_NOT_EQUAL = "Passwords not equal";
    public static final String CURRENT_PAGE = "current_page";

    public static final String EXCEPTION = "exception";
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
            return new ResponseContext(PathOfJsp.PASSWORD_CHANGE_PASSWORD_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        try {
            if (userService.changePassword(oldPassword, newPassword, String.valueOf(session.getAttribute(USER_ID)))) {
                session.setAttribute(CURRENT_PAGE, PathOfJsp.PASSWORD_CHANGED);
                return new ResponseContext(PathOfJsp.PASSWORD_CHANGED, ResponseContext.ResponseContextType.FORWARD);
            } else {
                request.setAttribute(RESULT_INFO, PASSWORD_NOT_EQUAL);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.PASSWORD_CHANGE_PASSWORD_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
