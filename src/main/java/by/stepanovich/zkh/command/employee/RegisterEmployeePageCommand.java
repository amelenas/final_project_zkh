package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegisterEmployeePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterEmployeePageCommand.class);
    private final WorkService workService = ServiceFactory.getInstance().getWorkService();

    private static final String EXCEPTION = "exception";
    private static final String SITES_OF_WORK = "sitesOfWork";
    private static final String TYPES_OF_WORKS = "typesOfWorks";
    private static final String CURRENT_PAGE = "current_page";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<SiteOfWork> sitesOfWork;
        List<TypeOfWork> typesOfWorks;
        try {
            sitesOfWork = workService.findAllSitesOfWork();
            typesOfWorks = workService.findAllTypesOfWork();
            request.setAttribute(SITES_OF_WORK, sitesOfWork);
            request.setAttribute(TYPES_OF_WORKS, typesOfWorks);

        } catch (ServiceException e) {
            LOGGER.error("Exception when register employee", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REGISTER_EMPLOYEE_PAGE_COMMAND);
        return new ResponseContext(PathOfJsp.SHOW_REGISTER_EMPLOYEE_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
