package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.common.ShowMainPageCommand;

import javax.servlet.http.HttpServletRequest;

public class ShowRegisterRequestPageCommand implements Command {
    private static final String LOG_IN = "log_in";

    @Override
    public ResponseContext execute(HttpServletRequest req) {
        if (req.getSession().getAttribute(LOG_IN) != null) {
            return new ShowMainPageCommand().execute(req);
        }
        return new ResponseContext(PathOfJsp.USER_REGISTER_ORDER, ResponseContext.ResponseContextType.FORWARD);
    }
}

