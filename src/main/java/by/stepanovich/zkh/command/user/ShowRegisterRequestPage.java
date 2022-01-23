package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;

public class ShowRegisterRequestPage implements Command {
    private static final String LOG_IN = "log_in";

    @Override
    public ResponseContext execute(RequestContent req) {
        if (req.getSessionAttribute(LOG_IN) != null) {
            return new ShowMainPageCommand().execute(req);
        }
        return new ResponseContext(PathOfJsp.USER_REGISTER_ORDER, ResponseContext.ResponseContextType.FORWARD);
    }
}

