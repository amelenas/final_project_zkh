package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.impl.UserServiceImpl;

import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterCommand implements Command {
    private static final String PHONE = "phone";
    private static final String USER_NAME = "userName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_SURNAME = "userSurname";
    private static final String SUCCESSFUL_MESSAGE = "successfulMessage";
    private static final String FAILED_REGISTER_MESSAGE = "failedRegisterMessage";

    private static final UserService USER_SERVICE = new UserServiceImpl() ;

    @Override
    public ResponseContext execute(RequestContent req) {
        ResourceBundle bundle
                = ResourceBundle.getBundle("generalKeys");

        System.out.println(req.getRequestParameter(EMAIL)[0]);
        System.out.println(req.getRequestParameter(PASSWORD)[0]+" "+req.getRequestParameter(USER_NAME)[0]+" "+
                req.getRequestParameter(USER_SURNAME)[0]+" "+req.getRequestParameter(PHONE)[0]);

              Optional<User> optionalUser = USER_SERVICE.register(req.getRequestParameter(EMAIL)[0],
                req.getRequestParameter(PASSWORD)[0], req.getRequestParameter(USER_NAME)[0],
                req.getRequestParameter(USER_SURNAME)[0], req.getRequestParameter(PHONE)[0]);

        if (optionalUser.isPresent()) {
            req.setRequestAttribute(SUCCESSFUL_MESSAGE, bundle.getString("message.register.success"));

        } else {
            req.setSessionAttribute(FAILED_REGISTER_MESSAGE, bundle.getString("message.register.failed"));
            return new ResponseContext(Path.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ShowMainPageCommand().execute(req);
    }
}
