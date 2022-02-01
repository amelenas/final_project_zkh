package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class ShowUserLoginPageCommand implements Command {
    private static final String LOG_IN = "log_in";

    @Override
    public ResponseContext execute(HttpServletRequest request) {

        if (request.getSession().getAttribute(LOG_IN) != null) {
               return new ShowMainPageCommand().execute(request);
        }
        return new ResponseContext(PathOfJsp.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
