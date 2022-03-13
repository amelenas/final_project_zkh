package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class ShowUserRegisterPageCommand implements Command {
    private static final String LOG_IN = "log_in";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ALREADY_REGISTERED = "You've already registered!";


    @Override
    public ResponseContext execute(HttpServletRequest request) {
        if (request.getSession().getAttribute(LOG_IN) != null) {
            request.setAttribute(ERROR_MESSAGE, ALREADY_REGISTERED);
            return new ShowMainPageCommand().execute(request);
        }
        return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
