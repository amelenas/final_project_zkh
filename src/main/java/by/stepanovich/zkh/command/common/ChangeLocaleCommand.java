package by.stepanovich.zkh.command.common;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    public static final String LOCALE = "locale";
    public static final String CURRENT_PAGE = "current_page";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        String locale = request.getParameter(LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE, locale);
        if (session.getAttribute(CURRENT_PAGE) == null) {
            return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);

        }
        return new ResponseContext(String.valueOf(session.getAttribute(CURRENT_PAGE)), ResponseContext.ResponseContextType.FORWARD);
    }
}
