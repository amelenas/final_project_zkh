package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowProfileCommand;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.impl.UserServiceImpl;

import java.util.Optional;

public class LogInCommand implements Command {

    private static final UserService USER_SERVICE = new UserServiceImpl();

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {

            return Path.SHOW_USER_LOGIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };


    @Override
    public ResponseContext execute(RequestContent req) {
        Optional<User> optionalUser = USER_SERVICE
                .login(req.getRequestParameter("email")[0], req.getRequestParameter("password")[0]);

        if (optionalUser.isPresent()) {
            setLoginAttributesIntoSession(req, optionalUser.get());
            return new ShowProfileCommand().execute(req);
        } else {
            req.setSessionAttribute("name", "Unregistered");
            return RESPONSE;
        }
    }

    private void setLoginAttributesIntoSession(RequestContent req, User user) {
        req.setSessionAttribute("id", user.getUserId());
        req.setSessionAttribute("email", user.getEmail());
        req.setSessionAttribute("name", user.getUserName());
        req.setSessionAttribute("role", user.getRole());
        req.setSessionAttribute("status", user.getUserStatus());
    }
}
