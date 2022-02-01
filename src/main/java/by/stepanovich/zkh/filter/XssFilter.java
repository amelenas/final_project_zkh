package by.stepanovich.zkh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) servletRequest),
                servletResponse);
    }
}
