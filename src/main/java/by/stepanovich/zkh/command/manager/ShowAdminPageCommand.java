package by.stepanovich.zkh.command.manager;

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
import java.util.TreeSet;

public class ShowAdminPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAdminPageCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private static final String EXCEPTION = "exception";
    private static final String USERS = "users";
    private static final String PAGE = "page";
    private static final String NO_OF_PAGES = "noOfPages";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        String page = request.getParameter(PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        try {
            TreeSet<User> users = new TreeSet<>(userService.findAllUsers());
            TreeSet<User> usersOnePage = new TreeSet<>(userService.findAllUsers(currentPage));
            int recordsPerPage = 10;
            int noOfRecords = (int) Math.ceil(users.size() * 1.0/ recordsPerPage);
            request.setAttribute(USERS, usersOnePage);
            request.setAttribute(NO_OF_PAGES, noOfRecords);
            request.setAttribute(PAGE, currentPage);
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching all users ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.ADD_ADMIN, ResponseContext.ResponseContextType.FORWARD);
    }
}
