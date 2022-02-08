package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class ShowPasswordPageCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        return new ResponseContext(PathOfJsp.PASSWORD_CHANGE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
