package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class SavePhoto implements Command {

    @Override
    public ResponseContext execute(RequestContent req) {
        return new ResponseContext(Path.SHOW_USER_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
