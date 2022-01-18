package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.impl.UserServiceImpl;

import java.util.Optional;
import java.util.ResourceBundle;

public class LogInCommand implements Command {
    public static final String AUTHORIZATION = "authorization";
    public static final String PROPERTY_NAME = "generalKeys";
    private static final String ROLE = "role";
    private static final String USER_ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String STATUS = "status";
    private static final String UNREGISTERED = "Unregistered";
    public static final String CURRENT_PAGE = "current_page";


    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {
        Optional<User> optionalUser = USER_SERVICE
                .login(req.getRequestParameter(EMAIL)[0], req.getRequestParameter(PASSWORD)[0]);

        if (optionalUser.isPresent()) {
            setLoginAttributesIntoSession(req, optionalUser.get());
req.setSessionAttribute(CURRENT_PAGE, Path.SHOW_PROFILE_PAGE);
            return new ResponseContext(Path.SHOW_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
        } else {
            req.setSessionAttribute(NAME, UNREGISTERED);
            final ResourceBundle generalKeys = ResourceBundle.getBundle(PROPERTY_NAME);
            req.setSessionAttribute("errorLoginMessage", generalKeys.getString("message.login.error"));

            return new ResponseContext(Path.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }

    private void setLoginAttributesIntoSession(RequestContent req, User user) {
        req.setSessionAttribute(AUTHORIZATION, Boolean.TRUE);
        req.setSessionAttribute(USER_ID, user.getUserId());
        req.setSessionAttribute(EMAIL, user.getEmail());
        req.setSessionAttribute(NAME, user.getUserName());
        req.setSessionAttribute(ROLE, user.getRole());
        req.setSessionAttribute(STATUS, user.getUserStatus());
        req.setSessionAttribute("errorLoginMessage", null);
    }
}
