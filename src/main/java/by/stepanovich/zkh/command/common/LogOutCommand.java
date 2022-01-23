package by.stepanovich.zkh.command.common;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class LogOutCommand implements Command {

    @Override
    public ResponseContext execute(RequestContent req) {
        req.setInvalidateSession(true);
        return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER, ResponseContext.ResponseContextType.FORWARD);
    }
}
