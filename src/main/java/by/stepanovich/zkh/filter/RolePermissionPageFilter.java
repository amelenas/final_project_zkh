package by.stepanovich.zkh.filter;

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

public class RolePermissionPageFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(RolePermissionPageFilter.class);
    private static final String ROLE = "role";

    private final Set<String> adminPages = new HashSet<>();
    private final Set<String> userPages = new HashSet<>();
    private final Set<String> employeePages = new HashSet<>();
    private final Set<String> defaultPages = new HashSet<>();

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
        Role userRole;
        if (request.getSession().getAttribute(ROLE) == null) {
            userRole = Role.UNREGISTERED;
        }else {
        userRole = Role.valueOf(request.getSession().getAttribute(ROLE).toString().toUpperCase());}
        Set<String> permittedPages;
        switch (userRole) {
            case ADMIN -> permittedPages = adminPages;
            case USER -> permittedPages = userPages;
            case EMPLOYEE -> permittedPages = employeePages;
            default -> permittedPages = defaultPages;
        }

        String requestPage = request.getRequestURI().replace(request.getContextPath(), "");

        if (permittedPages.contains(requestPage)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.info(String.format("Page is not in %s's Role scope: %s", userRole.name(), requestPage));
            request.getRequestDispatcher(PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER).forward(request, response);
        }
    }

    private void fillPermittedAdminPages() {
        adminPages.add(PathOfJsp.SHOW_MAIN_PAGE);
        adminPages.add(PathOfJsp.SHOW_USER_LOGIN_PAGE);
        adminPages.add(PathOfJsp.SHOW_REGISTER_PAGE);
        adminPages.add(PathOfJsp.SHOW_PROFILE_PAGE);
        adminPages.add(PathOfJsp.ERROR_500_PAGE);
        adminPages.add(PathOfJsp.BUILD_ORDER_REPORT);
        adminPages.add(PathOfJsp.BUILD_USER_REPORT);
        adminPages.add(PathOfJsp.MANAGER_PAGE);
        adminPages.add(PathOfJsp.COMMAND_PASSWORD_CHANGED);
        adminPages.add(PathOfJsp.PASSWORD_CHANGE_PAGE);
        adminPages.add(PathOfJsp.UPDATE_PROFILE_PAGE);
        adminPages.add(PathOfJsp.ASSIGN_PERFORMER_PAGE);
        adminPages.add(PathOfJsp.ASSIGN_PERFORMER);
        adminPages.add(PathOfJsp.CHOOSE_PERFORMER);
        adminPages.add(PathOfJsp.ADD_ADMIN);
        adminPages.add(PathOfJsp.CHECK_CLOSED_BY_EMPLOYEE_PAGE);
        defaultPages.add(PathOfJsp.ERROR_404_PAGE);
    }

    private void fillPermittedUserPages() {
        userPages.add(PathOfJsp.SHOW_MAIN_PAGE);
        userPages.add(PathOfJsp.SHOW_USER_LOGIN_PAGE);
        userPages.add(PathOfJsp.SHOW_REGISTER_PAGE);
        userPages.add(PathOfJsp.SHOW_PROFILE_PAGE);
        userPages.add(PathOfJsp.USER_REGISTER_ORDER);
        userPages.add(PathOfJsp.SHOW_REQUEST_ACCEPTED);
        userPages.add(PathOfJsp.ERROR_500_PAGE);
        userPages.add(PathOfJsp.ALL_USER_ORDERS_VIEW);
        userPages.add(PathOfJsp.COMMAND_PASSWORD_CHANGED);
        userPages.add(PathOfJsp.PASSWORD_CHANGE_PAGE);
        userPages.add(PathOfJsp.UPDATE_PROFILE_PAGE);
        userPages.add(PathOfJsp.SHOW_REGISTER_EMPLOYEE_PAGE);
        defaultPages.add(PathOfJsp.ERROR_404_PAGE);
    }

    private void fillPermittedEmployeePages() {
        employeePages.add(PathOfJsp.SHOW_MAIN_PAGE);
        employeePages.add(PathOfJsp.SHOW_USER_LOGIN_PAGE);
        employeePages.add(PathOfJsp.SHOW_REGISTER_PAGE);
        employeePages.add(PathOfJsp.SHOW_PROFILE_PAGE);
        employeePages.add(PathOfJsp.ALL_USER_ORDERS_COMMAND);
        employeePages.add(PathOfJsp.ERROR_500_PAGE);
        employeePages.add(PathOfJsp.COMMAND_PASSWORD_CHANGED);
        employeePages.add(PathOfJsp.PASSWORD_CHANGE_PAGE);
        employeePages.add(PathOfJsp.UPDATE_PROFILE_PAGE);
        employeePages.add(PathOfJsp.EMPLOYEE_TASK);
        defaultPages.add(PathOfJsp.ERROR_404_PAGE);
    }

    private void fillPermittedDefaultPages() {
        defaultPages.add(PathOfJsp.SHOW_MAIN_PAGE);
        defaultPages.add(PathOfJsp.SHOW_USER_LOGIN_PAGE);
        defaultPages.add(PathOfJsp.SHOW_REGISTER_PAGE);
        defaultPages.add(PathOfJsp.ERROR_500_PAGE);
        defaultPages.add(PathOfJsp.ERROR_404_PAGE);
    }

}
