package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UpdateProfileCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String EMAIL_EXIST = "email.exist";
    private static final String PHONE = "phone";
    private static final String USER_NAME = "userName";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USER_SURNAME = "userSurname";
    private static final String USER_ID = "id";
    private static final String EXCEPTION = "exception";
    private static final String MESSAGE = "message";
    private static final String CURRENT_PAGE = "current_page";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String firstName = request.getParameter(USER_NAME);
        String lastName = request.getParameter(USER_SURNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);

        String actualEmail = (String) session.getAttribute(EMAIL);
        try {
            User user = userService.findByEmail(email);
            if (!user.equals(userService.findByEmail(actualEmail))) {
                LOGGER.error("This email is already registered " + email);
                request.setAttribute(MESSAGE, EMAIL_EXIST);
                session.setAttribute(CURRENT_PAGE, PathOfJsp.UPDATE_PROFILE_PAGE);
                return new ResponseContext(PathOfJsp.UPDATE_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
            } else {
                try {
                    User newUser = userService.updateUserData(Long.parseLong(String.valueOf(session.getAttribute(USER_ID))), firstName, lastName, email, phone);
                    if (newUser != null) {
                        session.setAttribute(NAME, newUser.getUserName());
                        session.setAttribute(USER_SURNAME, newUser.getUserSurname());
                        session.setAttribute(PHONE, newUser.getPhone());
                        session.setAttribute(EMAIL, newUser.getEmail());
                        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE_COMMAND);
                        return new ResponseContext(PathOfJsp.SHOW_PROFILE_PAGE_COMMAND, ResponseContext.ResponseContextType.REDIRECT);
                    }
                    request.setAttribute(MESSAGE, INVALID_INPUT);
                    session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
                    return new ResponseContext(PathOfJsp.UPDATE_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
                } catch (ServiceException ex) {
                    request.setAttribute(EXCEPTION, ex);
                    LOGGER.error("Exception when updating user by email", ex);
                    return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
                }
            }
        } catch (ServiceException e) {
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);

        }
    }
}
