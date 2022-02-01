package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.tablebuild.ReportSet;

import javax.servlet.http.HttpServletRequest;

public class ShowAllUsersCommand implements Command {
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    public static final String EXCEPTION = "exception";
    public static final String USER_BEAN = "userBean";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        ReportSet userSet;
        try {
            userSet = new ReportSet(USER_SERVICE.findAllUsers());
            request.setAttribute(USER_BEAN, userSet);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.BUILD_USER_REPORT, ResponseContext.ResponseContextType.FORWARD);
    }
}
