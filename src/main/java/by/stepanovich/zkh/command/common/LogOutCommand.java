package by.stepanovich.zkh.command.common;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER, ResponseContext.ResponseContextType.FORWARD);
    }
}
