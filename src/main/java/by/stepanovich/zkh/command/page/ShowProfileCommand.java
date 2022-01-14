package by.stepanovich.zkh.command.page;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.impl.UserServiceImpl;

import java.util.Optional;

public class ShowProfileCommand implements Command {

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_PROFILE_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {

        final Optional<User> optionalUser
                = USER_SERVICE.findById((Integer) req.getSessionAttribute("id"));
        if (optionalUser.isEmpty()) {
            return new ShowMainPageCommand().execute(req);
        }

        req.setRequestAttribute("user", optionalUser.get());

        return RESPONSE;
    }
}
