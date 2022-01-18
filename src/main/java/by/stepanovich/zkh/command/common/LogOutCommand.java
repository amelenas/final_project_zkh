package by.stepanovich.zkh.command.common;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class LogOutCommand implements Command {

    @Override
    public ResponseContext execute(RequestContent req) {
        req.setInvalidateSession(true);
        return new ResponseContext(Path.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
