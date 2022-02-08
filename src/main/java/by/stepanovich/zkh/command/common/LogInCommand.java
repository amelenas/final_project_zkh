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

public class LogInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);
    private static final String AUTHORIZATION = "authorization";
    private static final String ROLE = "role";
    private static final String USER_ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "userSurname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String PASSWORD = "password";
    private static final String STATUS = "status";
    private static final String CURRENT_PAGE = "current_page";
    private static final String LOGIN_MESSAGE = "loginMessage";
    private static final String INVALID_CREDENTIALS = "login.invalid.credentials";

    private UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        Optional<User> optionalUser = Optional.empty();
        HttpSession session = request.getSession();
        try {
            optionalUser = USER_SERVICE
                    .login(request.getParameter(EMAIL), request.getParameter(PASSWORD));
        } catch (ServiceException e) {
            LOGGER.error("Exception in LogInCommand", e);
        }

        if (optionalUser.isPresent()) {
            setLoginAttributesIntoSession(request, optionalUser.get());
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
            return new ShowProfileCommand().execute(request);
        } else {
            request.setAttribute(LOGIN_MESSAGE, INVALID_CREDENTIALS);
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_USER_LOGIN_PAGE);
            return new ResponseContext(PathOfJsp.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }

    private void setLoginAttributesIntoSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(AUTHORIZATION, Boolean.TRUE);
        session.setAttribute(USER_ID, user.getUserId());
        session.setAttribute(PHONE, user.getPhone());
        session.setAttribute(EMAIL, user.getEmail());
        session.setAttribute(NAME, user.getUserName());
        session.setAttribute(SURNAME, user.getUserSurname());
        session.setAttribute(ROLE, user.getRole());
        session.setAttribute(STATUS, user.getUserStatus());
        session.setAttribute(LOGIN_MESSAGE, null);
    }
}
