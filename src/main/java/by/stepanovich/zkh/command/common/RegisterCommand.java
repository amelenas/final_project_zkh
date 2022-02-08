package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
    private static final String PHONE = "phone";
    private static final String USER_NAME = "userName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "repeatPassword";
    private static final String USER_SURNAME = "userSurname";
    private static final String FAILED_REGISTER_MESSAGE = "errorMessage";
    private static final String CURRENT_PAGE = "current_page";
    private static final String PASSWORD_NOT_EQUAL = "password.not.equal";
    private static final String DATA_NOT_VALID = "login.or.password.is.not.valid";

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> optionalUser;
        if (!request.getParameter(REPEAT_PASSWORD).equals(request.getParameter(PASSWORD))) {
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REGISTER_PAGE);
            request.setAttribute(FAILED_REGISTER_MESSAGE, PASSWORD_NOT_EQUAL);
            return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        try {
            optionalUser = userService.register(request.getParameter(EMAIL),
                    request.getParameter(PASSWORD), request.getParameter(USER_NAME),
                    request.getParameter(USER_SURNAME), request.getParameter(PHONE));
        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterCommand", e);
            request.setAttribute(FAILED_REGISTER_MESSAGE, DATA_NOT_VALID);
            return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }

        if (optionalUser.isPresent()) {
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REGISTER_SUCCESS);
            return new ResponseContext(PathOfJsp.SHOW_REGISTER_SUCCESS, ResponseContext.ResponseContextType.REDIRECT);
        } else {
            request.setAttribute(FAILED_REGISTER_MESSAGE, DATA_NOT_VALID);
        }
        return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
