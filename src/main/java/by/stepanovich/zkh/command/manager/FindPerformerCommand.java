package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class FindPerformerCommand implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private WorkService workService = ServiceFactory.getInstance().getWorkService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {

        long typeOfWorksId = Long.parseLong(request.getParameter("typeOfWorks"));
        long siteOfWork = Long.parseLong(request.getParameter("siteOfWork"));


        return new ResponseContext(PathOfJsp.ASSIGN_PERFORMER, ResponseContext.ResponseContextType.FORWARD);
    }
}
