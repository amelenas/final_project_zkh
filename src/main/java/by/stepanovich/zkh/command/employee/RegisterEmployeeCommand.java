package by.stepanovich.zkh.command.employee;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterEmployeeCommand implements Command {
    private final WorkService workService = ServiceFactory.getInstance().getWorkService();
    private static final Logger LOGGER = LogManager.getLogger(RegisterEmployeeCommand.class);

    private static final String TYPE_OF_WORKS = "typeOfWorks";
    private static final String SITE_OF_WORK = "siteOfWork";
    private static final String USER_ID = "id";
    private static final String EXCEPTION = "exception";
    private static final String CURRENT_PAGE = "current_page";


    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REGISTER_EMPLOYEE_PAGE_COMMAND);
        String[] typeOfWorks = request.getParameterValues(TYPE_OF_WORKS);
        String[] siteOfWork = request.getParameterValues(SITE_OF_WORK);
        long userId = Long.parseLong(String.valueOf(session.getAttribute(USER_ID)));

        for (String siteOfWorkId : siteOfWork) {
            for (String typeOfWorkId : typeOfWorks) {
                try {
                    workService.registerEmployee(Long.parseLong(siteOfWorkId), Long.parseLong(typeOfWorkId), userId);

                } catch (ServiceException e) {
                    LOGGER.error("Exception when register employee", e);
                    request.setAttribute(EXCEPTION, e);
                    return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
                }
            }
        }
        session.setAttribute(CURRENT_PAGE, PathOfJsp.REGISTER_AS_EMPLOYEE_SUCCESS_COMMAND);
        return new ResponseContext(PathOfJsp.REGISTER_AS_EMPLOYEE_SUCCESS_COMMAND, ResponseContext.ResponseContextType.REDIRECT);

    }
}
