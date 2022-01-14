package by.stepanovich.zkh.command.page;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Role;

public class ShowMainPageCommand implements Command {

    private static final ResponseContext GUEST_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext EMPLOYEE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_EMPLOYEE_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ADMIN_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_ADMIN_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext USER_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {

        if (req.getSessionAttribute("role") == null) {
            return GUEST_RESPONSE;
        }
        if (Role.EMPLOYEE.equals(req.getSessionAttribute("role"))) {

            return EMPLOYEE_RESPONSE;
        }
        if (Role.ADMIN.equals(req.getSessionAttribute("role"))) {

            return ADMIN_RESPONSE;
        }
        if (Role.USER.equals(req.getSessionAttribute("role"))) {

            return USER_RESPONSE;
        }
        return GUEST_RESPONSE;
    }
}
