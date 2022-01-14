package by.stepanovich.zkh.command.user;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class LogOutCommand implements Command {

    private static final ResponseContext RESPONSE = new ResponseContext() {

        @Override
        public String getPage() {
            return "/controller";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        req.setInvalidateSession(true);
        return RESPONSE;
    }
}
