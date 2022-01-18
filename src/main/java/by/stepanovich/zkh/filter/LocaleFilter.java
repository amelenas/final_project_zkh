package by.stepanovich.zkh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns = {"/jsp/*"})
public class LocaleFilter implements Filter {
    public static final String LOCALE = "locale";
    private static final String EN = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (httpRequest.getSession(false) != null
                && httpRequest.getSession(false).getAttribute(LOCALE) == null) {
            httpRequest.getSession().setAttribute(LOCALE, EN);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {Filter.super.destroy();
    }
}
