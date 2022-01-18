package by.stepanovich.zkh.command.page;


import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;

public class ChangeLocaleCommand implements Command {

    public static final String LOCALE = "locale";
    public static final String CURRENT_PAGE = "current_page";
    @Override
    public ResponseContext execute(RequestContent request) {
        String locale = request.getRequestParameter(LOCALE)[0];
        request.setSessionAttribute(LOCALE, locale);
        if (request.getSessionAttribute(CURRENT_PAGE) == null) {
            return new ResponseContext(Path.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);

        }
        return new ResponseContext(String.valueOf(request.getSessionAttribute(CURRENT_PAGE)), ResponseContext.ResponseContextType.FORWARD);

    }
}
