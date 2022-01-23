package by.stepanovich.zkh.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {
    public static final String CURRENT_PAGE = "current_page";
    public static final String CONTAINS_JSP = "jsp/";
    public static final String CONTAINS_CONTROLLER = "controller";
    public static final String CONTAINS_CHANGE_LOCALE = "command=change_locale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String currentPage = httpRequest.getRequestURL().toString();
        if (currentPage.contains(CONTAINS_JSP)) {
            int index = currentPage.indexOf(CONTAINS_JSP);
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(CURRENT_PAGE, currentPage);
        } else if (currentPage.contains(CONTAINS_CONTROLLER) && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null &&
                !httpRequest.getQueryString().contains(CONTAINS_CHANGE_LOCALE)) {
            int index = currentPage.indexOf(CONTAINS_CONTROLLER);
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();

            httpRequest.getSession().setAttribute(CURRENT_PAGE, currentPage);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }
}
