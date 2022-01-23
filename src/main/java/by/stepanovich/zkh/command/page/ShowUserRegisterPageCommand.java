package by.stepanovich.zkh.command.page;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class ShowUserRegisterPageCommand implements Command {
    private static final String LOG_IN = "log_in";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public ResponseContext execute(RequestContent req) {
        if (req.getSessionAttribute(LOG_IN) != null) {
            req.setRequestAttribute(ERROR_MESSAGE, "You've already registered!");
            return new ShowMainPageCommand().execute(req);
        }
        return new ResponseContext(PathOfJsp.SHOW_REGISTER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
