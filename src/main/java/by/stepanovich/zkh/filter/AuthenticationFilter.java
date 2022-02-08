package by.stepanovich.zkh.filter;

import by.stepanovich.zkh.command.PathOfJsp;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private ServletContext context;

    private static final String COMMAND_PARAMETER_NAME = "command";
    private static final String ROLE = "role";
    private static final String INDEX_JSP = "index.jsp";
    private static final String SHOW_REGISTER = "show_register";
    private static final String CHANGE_LOCALE = "change_locale";
    private static final String SHOW_MAIN_PAGE = "show_main_page";
    private static final String SHOW_LOGIN = "show_login";
    private static final String CONTROLLER = "controller";
    private static final String USER_ID = "id";
    private static final String LOG_IN = "log_in";
    private static final String REGISTER_SUCCESS_PAGE = "register_success_page";
    private static final String REGISTER = "register";
    private static final String IMAGE = "image";

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        String uri = req.getRequestURI();

        Object role = req.getSession().getAttribute(ROLE);
        Object userId = req.getSession().getAttribute(USER_ID);

        if (uri.endsWith(INDEX_JSP) || uri.contains(IMAGE) || commandName.equals(SHOW_REGISTER) || commandName.equals(CONTROLLER)
                || commandName.equals(SHOW_MAIN_PAGE) || commandName.equals(CHANGE_LOCALE)
                || commandName.equals(SHOW_LOGIN) || commandName.equals(REGISTER_SUCCESS_PAGE) || commandName.equals(LOG_IN) || commandName.equals(REGISTER)) {
            chain.doFilter(request, response);
        } else {
            if (role == null || userId == null) {
                res.sendRedirect(PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER);
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}
