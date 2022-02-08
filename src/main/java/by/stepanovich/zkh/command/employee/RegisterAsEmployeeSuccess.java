package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;

import javax.servlet.http.HttpServletRequest;

public class RegisterAsEmployeeSuccess implements Command {
    @Override
    public ResponseContext execute(HttpServletRequest request) {
        return new ResponseContext(PathOfJsp.REGISTER_AS_EMPLOYEE_SUCCESS, ResponseContext.ResponseContextType.FORWARD);
    }
}
