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
import java.util.Optional;

public class ShowProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowProfileCommand.class);
    private static final String USER_ID = "id";
    private static final String USER = "user";
    private UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {

        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = USER_SERVICE.findById((Long) request.getSession().getAttribute(USER_ID));
        } catch (ServiceException e) {
           LOGGER.error("Exception in ShowProfileCommand");
        }
        if (optionalUser.isEmpty()) {
            return new ShowMainPageCommand().execute(request);
        }
        request.setAttribute(USER, optionalUser.get());
        return new ResponseContext(PathOfJsp.SHOW_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
