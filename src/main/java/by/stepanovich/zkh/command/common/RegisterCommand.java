package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.service.impl.UserServiceImpl;
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
    private static final String LOGIN_MESSAGE = "loginMessage";
    public static final String CURRENT_PAGE = "current_page";
    public static final String REGISTER_SUCCESS = "login.register.success";
    public static final String REGISTER_FAILED = "login.or.password.is.not.valid";

    private UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> optionalUser = Optional.empty();
        if (!request.getParameter(REPEAT_PASSWORD).equals(request.getParameter(PASSWORD))) {
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REGISTER_PAGE);
            request.setAttribute(FAILED_REGISTER_MESSAGE, REGISTER_FAILED);
            return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        try {
            optionalUser = USER_SERVICE.register(request.getParameter(EMAIL),
                    request.getParameter(PASSWORD), request.getParameter(USER_NAME),
                    request.getParameter(USER_SURNAME), request.getParameter(PHONE));
        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterCommand", e);
        }

        if (optionalUser.isPresent()) {
            request.setAttribute(LOGIN_MESSAGE, REGISTER_SUCCESS);
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_USER_LOGIN_PAGE);
            return new ResponseContext(PathOfJsp.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
        } else {
            session.setAttribute(FAILED_REGISTER_MESSAGE, REGISTER_FAILED);
        }
        return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
