package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowProfileCommand;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ResourceBundle;

public class LogInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);
    public static final String AUTHORIZATION = "authorization";
    public static final String PROPERTY_NAME = "generalKeys";
    private static final String ROLE = "role";
    private static final String USER_ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "userSurname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String STATUS = "status";
    public static final String CURRENT_PAGE = "current_page";
    public static final String ERROR_LOGIN_MASSAGE = "errorLoginMessage";


    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = USER_SERVICE
                    .login(req.getRequestParameter(EMAIL)[0], req.getRequestParameter(PASSWORD)[0]);
        } catch (ServiceException e) {
            LOGGER.error("Exception in LogInCommand", e);
        }

        if (optionalUser.isPresent()) {
            setLoginAttributesIntoSession(req, optionalUser.get());
            req.setSessionAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
            return new ShowProfileCommand().execute(req);
        } else {
            ResourceBundle generalKeys = ResourceBundle.getBundle(PROPERTY_NAME);
            req.setSessionAttribute(ERROR_LOGIN_MASSAGE, generalKeys.getString("message.login.error"));
            req.setSessionAttribute(CURRENT_PAGE, PathOfJsp.SHOW_USER_LOGIN_PAGE);
            return new ResponseContext(PathOfJsp.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }

    private void setLoginAttributesIntoSession(RequestContent req, User user) {
        req.setSessionAttribute(AUTHORIZATION, Boolean.TRUE);
        req.setSessionAttribute(USER_ID, user.getUserId());
        req.setSessionAttribute(EMAIL, user.getEmail());
        req.setSessionAttribute(NAME, user.getUserName());
        req.setSessionAttribute(SURNAME, user.getUserSurname());
        req.setSessionAttribute(ROLE, user.getRole());
        req.setSessionAttribute(STATUS, user.getUserStatus());
        req.setSessionAttribute(ERROR_LOGIN_MASSAGE, null);
    }
}
