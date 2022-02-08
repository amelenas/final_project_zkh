package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class ShowUserLoginPageCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
      return new ResponseContext(PathOfJsp.SHOW_USER_LOGIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
