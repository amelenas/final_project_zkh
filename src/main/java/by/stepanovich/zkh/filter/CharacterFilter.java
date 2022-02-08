package by.stepanovich.zkh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharacterFilter implements Filter {
    private final static String ENCODING_UTF_8 = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(ENCODING_UTF_8);
        response.setCharacterEncoding(ENCODING_UTF_8);

        filterChain.doFilter(request, response);
    }
}

