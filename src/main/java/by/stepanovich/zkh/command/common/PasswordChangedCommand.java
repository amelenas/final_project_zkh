package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class PasswordChangedCommand implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        return new ResponseContext(PathOfJsp.PASSWORD_CHANGED, ResponseContext.ResponseContextType.FORWARD);
    }
}
