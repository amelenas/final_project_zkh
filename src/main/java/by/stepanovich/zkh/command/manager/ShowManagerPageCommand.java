package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class ShowManagerPageCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        return new ResponseContext(PathOfJsp.MANAGER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
