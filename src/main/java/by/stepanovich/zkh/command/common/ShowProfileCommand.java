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

public class ShowProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowProfileCommand.class);
    private static final String USER_ID = "id";
    private static final String USER = "user";
    private static final String EXCEPTION = "exception";
    private UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        User user;
        try {
            long userId = Long.parseLong(String.valueOf(request.getSession().getAttribute(USER_ID)));
            user = USER_SERVICE.findById(userId);
        } catch (ServiceException e) {
            LOGGER.error("Exception in ShowProfileCommand");
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setAttribute(USER, user);
        return new ResponseContext(PathOfJsp.SHOW_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
