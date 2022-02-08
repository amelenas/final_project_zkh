package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class RequestAcceptedPageCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
      return new ResponseContext(PathOfJsp.SHOW_REQUEST_ACCEPTED, ResponseContext.ResponseContextType.FORWARD);
    }
}
