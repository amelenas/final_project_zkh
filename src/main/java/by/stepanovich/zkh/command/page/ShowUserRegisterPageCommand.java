package by.stepanovich.zkh.command.page;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class ShowUserRegisterPageCommand implements Command {

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_REGISTER_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        if (req.getSessionAttribute("log_in") != null) {
            req.setRequestAttribute("errorMessage", "You've already registered!");
            return new ShowMainPageCommand().execute(req);
        }
        return RESPONSE;
    }
}
