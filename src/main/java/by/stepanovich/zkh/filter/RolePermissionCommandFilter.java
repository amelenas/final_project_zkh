package by.stepanovich.zkh.filter;

import by.stepanovich.zkh.command.CommandName;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RolePermissionCommandFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(RolePermissionCommandFilter.class);
    private static final String ROLE = "role";
    private static final String COMMAND_PARAMETER_NAME = "command";
    private final Set<CommandName> adminCommands = new HashSet<>();
    private final Set<CommandName> userCommands = new HashSet<>();
    private final Set<CommandName> employeeCommands = new HashSet<>();
    private final Set<CommandName> defaultCommands = new HashSet<>();
    CommandName commandType;

    @Override
    public void init(FilterConfig filterConfig) {
        fillPermittedAdminPages();
        fillPermittedUserPages();
        fillPermittedEmployeePages();
        fillPermittedDefaultPages();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        Role userRole;
        if (request.getSession().getAttribute(ROLE) == null) {
            userRole = Role.UNREGISTERED;
        } else {
            userRole = Role.valueOf(request.getSession().getAttribute(ROLE).toString().toUpperCase());
        }
        Set<CommandName> permittedPages;
        switch (userRole) {
            case ADMIN -> permittedPages = adminCommands;
            case USER -> permittedPages = userCommands;
            case EMPLOYEE -> permittedPages = employeeCommands;
            default -> permittedPages = defaultCommands;
        }

        if (commandName != null) {
            commandType = CommandName.valueOf(commandName.toUpperCase());
        }
        if (permittedPages.contains(commandType)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.info(String.format("Page is not in %s's Role scope: %s", userRole.name(), commandType));
            request.getRequestDispatcher(PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER).forward(request, response);
        }
    }

    private void fillPermittedAdminPages() {
        adminCommands.add(CommandName.CHANGE_LOCALE);
        adminCommands.add(CommandName.SHOW_MAIN_PAGE);
        adminCommands.add(CommandName.UPDATE_PASSWORD);
        adminCommands.add(CommandName.PASSWORD_CHANGE_PAGE);
        adminCommands.add(CommandName.PASSWORD_CHANGED);
        adminCommands.add(CommandName.UPDATE_PROFILE_PAGE);
        adminCommands.add(CommandName.UPDATE_PROFILE);
        adminCommands.add(CommandName.SHOW_LOGIN);
        adminCommands.add(CommandName.LOG_IN);
        adminCommands.add(CommandName.LOG_OUT);
        adminCommands.add(CommandName.USER_INFO);
        adminCommands.add(CommandName.MANAGER_TASK);
        adminCommands.add(CommandName.SHOW_ALL_USERS);
        adminCommands.add(CommandName.SHOW_ALL_ORDERS);
        adminCommands.add(CommandName.ASSIGN_PERFORMER_PAGE);
        adminCommands.add(CommandName.ASSIGN_PERFORMER);
        adminCommands.add(CommandName.FIND_PERFORMER);
        adminCommands.add(CommandName.CHOOSE_PERFORMER);
        adminCommands.add(CommandName.SHOW_ADD_ADMIN_PAGE);
        adminCommands.add(CommandName.ADD_ADMIN);
        adminCommands.add(CommandName.REGISTER_EMPLOYEE);
        adminCommands.add(CommandName.ACCEPT_EMPLOYEE_PAGE);
        adminCommands.add(CommandName.MAKE_USER);
        adminCommands.add(CommandName.ADD_EMPLOYEE);
        adminCommands.add(CommandName.REJECT_EMPLOYEE);
        adminCommands.add(CommandName.SENT_TO_WORK);
        adminCommands.add(CommandName.SHOW_ORDERS_AT_WORK);
        adminCommands.add(CommandName.CANCEL_EMPLOYEE_ORDER);
        adminCommands.add(CommandName.CHECK_CLOSED_APPLICATIONS);
        adminCommands.add(CommandName.CLOSE_ORDER);

    }

    private void fillPermittedUserPages() {
        userCommands.add(CommandName.CHANGE_LOCALE);
        userCommands.add(CommandName.SHOW_MAIN_PAGE);
        userCommands.add(CommandName.UPDATE_PASSWORD);
        userCommands.add(CommandName.PASSWORD_CHANGE_PAGE);
        userCommands.add(CommandName.PASSWORD_CHANGED);
        userCommands.add(CommandName.UPDATE_PROFILE_PAGE);
        userCommands.add(CommandName.UPDATE_PROFILE);
        userCommands.add(CommandName.SHOW_LOGIN);
        userCommands.add(CommandName.LOG_IN);
        userCommands.add(CommandName.LOG_OUT);
        userCommands.add(CommandName.USER_INFO);
        userCommands.add(CommandName.REGISTER_AS_EMPLOYEE_SUCCESS);
        userCommands.add(CommandName.SHOW_REGISTER);
        userCommands.add(CommandName.REGISTER);
        userCommands.add(CommandName.REGISTER_SUCCESS_PAGE);
        userCommands.add(CommandName.SHOW_REGISTER_REQUEST_PAGE);
        userCommands.add(CommandName.REGISTER_ORDER);
        userCommands.add(CommandName.REGISTER_ORDER_SUCCESS);
        userCommands.add(CommandName.SHOW_ALL_USER_ORDERS);
        userCommands.add(CommandName.CANCEL_SINGLE_ORDER);
        userCommands.add(CommandName.REGISTER_EMPLOYEE_PAGE);
        userCommands.add(CommandName.REGISTER_EMPLOYEE);
    }

    private void fillPermittedEmployeePages() {
        employeeCommands.add(CommandName.CHANGE_LOCALE);
        employeeCommands.add(CommandName.SHOW_MAIN_PAGE);
        employeeCommands.add(CommandName.UPDATE_PASSWORD);
        employeeCommands.add(CommandName.PASSWORD_CHANGE_PAGE);
        employeeCommands.add(CommandName.PASSWORD_CHANGED);
        employeeCommands.add(CommandName.UPDATE_PROFILE_PAGE);
        employeeCommands.add(CommandName.UPDATE_PROFILE);
        employeeCommands.add(CommandName.SHOW_LOGIN);
        employeeCommands.add(CommandName.LOG_IN);
        employeeCommands.add(CommandName.LOG_OUT);
        employeeCommands.add(CommandName.USER_INFO);
        employeeCommands.add(CommandName.EMPLOYEE_TASK);
        employeeCommands.add(CommandName.TAKE_ORDER);
        employeeCommands.add(CommandName.CANCEL_EMPLOYEE_ORDER);
        employeeCommands.add(CommandName.CLOSE_BY_EMPLOYEE_ORDER);
        employeeCommands.add(CommandName.ASSIGN_PERFORMER_PAGE);
    }

    private void fillPermittedDefaultPages() {
        defaultCommands.add(CommandName.CHANGE_LOCALE);
        defaultCommands.add(CommandName.SHOW_MAIN_PAGE);
        defaultCommands.add(CommandName.UPDATE_PASSWORD);
        defaultCommands.add(CommandName.PASSWORD_CHANGE_PAGE);
        defaultCommands.add(CommandName.PASSWORD_CHANGED);
        defaultCommands.add(CommandName.UPDATE_PROFILE_PAGE);
        defaultCommands.add(CommandName.UPDATE_PROFILE);
        defaultCommands.add(CommandName.SHOW_LOGIN);
        defaultCommands.add(CommandName.LOG_IN);
        defaultCommands.add(CommandName.LOG_OUT);
        defaultCommands.add(CommandName.USER_INFO);
        defaultCommands.add(CommandName.SHOW_REGISTER);
        defaultCommands.add(CommandName.REGISTER);
        defaultCommands.add(CommandName.REGISTER_SUCCESS_PAGE);
    }
}
