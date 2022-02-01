package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.User;
import by.stepanovich.zkh.service.UserService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UpdateProfileCommand implements Command {
    private  UserService userService = ServiceFactory.getInstance().getUserService();

    private static final String INVALID_INPUT = "inputData.invalid";
    private static final String PROFILE_UPDATED = "profile.updated";
    private static final String EMAIL_IS_NOT_FREE = "update.profile.email.notFree";
    private static final String PHONE = "phone";
    private static final String USER_NAME = "userName";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String USER_SURNAME = "userSurname";
    private static final String USER_ID = "id";
    public static final String EXCEPTION = "exception";
    public static final String MESSAGE = "message";
    public static final String CURRENT_PAGE = "current_page";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<User> newUser;
        String firstName = request.getParameter(USER_NAME);
        String lastName = request.getParameter(USER_SURNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);

        String actualEmail = (String) session.getAttribute(EMAIL);
        Optional<User> userOptional;
        try {
            userOptional = userService.findByEmail(email);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }

        if (userOptional.isPresent() || actualEmail.equals(email)) {
            request.setAttribute(MESSAGE, EMAIL_IS_NOT_FREE);
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
            return new ResponseContext(PathOfJsp.UPDATE_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }

        try {
            newUser = userService.updateUserData(Long.parseLong(String.valueOf(session.getAttribute(USER_ID))),firstName, lastName, email, phone);
            if (newUser.isPresent()) {
                session.setAttribute(NAME, newUser.get().getUserName());
                session.setAttribute(USER_SURNAME, newUser.get().getUserSurname());
                session.setAttribute(PHONE, newUser.get().getPhone());
                session.setAttribute(EMAIL, newUser.get().getEmail());
                request.setAttribute(MESSAGE, PROFILE_UPDATED);
                session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
                return new ResponseContext(PathOfJsp.SHOW_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
            }
            request.setAttribute(MESSAGE, INVALID_INPUT);
            session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_PROFILE_PAGE);
            return new ResponseContext(PathOfJsp.UPDATE_PROFILE_PAGE, ResponseContext.ResponseContextType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
    }
}
