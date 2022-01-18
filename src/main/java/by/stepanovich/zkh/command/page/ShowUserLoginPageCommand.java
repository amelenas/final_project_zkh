package by.stepanovich.zkh.command.page;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class ShowUserLoginPageCommand implements Command {
    private static final String LOG_IN = "log_in";

    @Override
    public ResponseContext execute(RequestContent req) {

        if (req.getSessionAttribute(LOG_IN) != null) {
               return new ShowMainPageCommand().execute(req);
        }
        return new ResponseContext(Path.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
