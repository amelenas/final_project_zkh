package by.stepanovich.zkh.command.page;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ShowProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowProfileCommand.class);
    private static final String USER_ID = "id";
    private static final String USER = "user";
    private static final UserService USER_SERVICE = new UserServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {

        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = USER_SERVICE.findById((Long) req.getSessionAttribute(USER_ID));
        } catch (ServiceException e) {
           LOGGER.error("Exception in ShowProfileCommand");
        }
        if (optionalUser.isEmpty()) {
            return new ShowMainPageCommand().execute(req);
        }
        req.setRequestAttribute(USER, optionalUser.get());
        return new ResponseContext(PathOfJsp.SHOW_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
