package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class SavePhotoCommand implements Command {

    @Override
    public ResponseContext execute(RequestContent req) {
        return new ResponseContext(PathOfJsp.USER_REGISTER_ORDER, ResponseContext.ResponseContextType.FORWARD);
    }
}
