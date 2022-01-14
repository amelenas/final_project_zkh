package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.impl.UserServiceImpl;

import java.util.Optional;

public class RegisterCommand implements Command {

    private static final UserService USER_SERVICE = new UserServiceImpl() ;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_REGISTER_AGAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {

              Optional<User> optionalUser = USER_SERVICE.register(req.getRequestParameter("email")[0],
                req.getRequestParameter("password")[0], req.getRequestParameter("userName")[0],
                req.getRequestParameter("userSurname")[0], req.getRequestParameter("phone")[0]);

        if (optionalUser.isPresent()) {
            //successfulMessage
        } else {
            //failedMessage
            return RESPONSE;
        }
        return new ShowMainPageCommand().execute(req);
    }
}
