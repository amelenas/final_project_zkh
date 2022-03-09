package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FindPerformerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FindPerformerCommand.class);

    private static final String EXCEPTION = "exception";
    private static final String PERFORMERS = "performers";
    private static final String REGISTRATION_ID = "registrationId";
    private static final String CURRENT_PAGE = "current_page";
    private static final String TYPE_OF_WORKS = "typeOfWorks";
    private static final String SITE_OF_WORK = "siteOfWork";

    private WorkService workService = ServiceFactory.getInstance().getWorkService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long orderId;
        long typeOfWorksId;
        long siteOfWork;
        if (request.getParameter(REGISTRATION_ID) != null ||
                request.getParameter(TYPE_OF_WORKS) != null ||
                request.getParameter(SITE_OF_WORK) != null) {
            orderId = Long.parseLong(request.getParameter(REGISTRATION_ID));
            typeOfWorksId = Long.parseLong(request.getParameter(TYPE_OF_WORKS));
            siteOfWork = Long.parseLong(request.getParameter(SITE_OF_WORK));
        } else {
            orderId = Long.parseLong(String.valueOf(session.getAttribute(REGISTRATION_ID)));
            typeOfWorksId = Long.parseLong(String.valueOf(session.getAttribute(TYPE_OF_WORKS)));
            siteOfWork = Long.parseLong(String.valueOf(session.getAttribute(SITE_OF_WORK)));
        }

        List<Long> performersID;
        Set<User> performers = new TreeSet<>();

        try {
            performersID = workService.findPerformerForWork(typeOfWorksId, siteOfWork);
            for (Long idPerformer : performersID) {
                performers.add(userService.findById(idPerformer));
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching for performer", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        session.setAttribute(TYPE_OF_WORKS, typeOfWorksId);
        session.setAttribute(SITE_OF_WORK, siteOfWork);
        session.setAttribute(PERFORMERS, performers);
        session.setAttribute(REGISTRATION_ID, orderId);
        session.setAttribute(CURRENT_PAGE, PathOfJsp.FIND_PERFORMER_COMMAND);

        return new ResponseContext(PathOfJsp.CHOOSE_PERFORMER, ResponseContext.ResponseContextType.FORWARD);
    }
}
