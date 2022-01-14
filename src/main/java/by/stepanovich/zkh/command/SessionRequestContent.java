package by.stepanovich.zkh.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent implements RequestContent {

    private final HashMap<String, Object> requestAttributes = new HashMap<>();
    private final HashMap<String, String[]> requestParameters = new HashMap<>();
    private final HashMap<String, Object> sessionAttributes = new HashMap<>();
    private final HashMap<String, String> servletContextParameters = new HashMap<>();
    private boolean invalidateSession = false;

    @Override
    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    @Override
    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    SessionRequestContent() {
    }

    @Override
    public void extractValues(HttpServletRequest request) {

        requestParameters.putAll(request.getParameterMap());

        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            sessionAttributes.put(key, session.getAttribute(key));
        }

        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            String key = requestAttributeNames.nextElement();
            requestAttributes.put(key, request.getAttribute(key));
        }

        ServletContext servletContext = request.getServletContext();
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String key = initParameterNames.nextElement();
            servletContextParameters.put(key, servletContext.getInitParameter(key));
        }
    }

    @Override
    public void insertAttributes(HttpServletRequest request) {

        if (isInvalidateSession()) {
            request.getSession().invalidate();
            return;
        }

        HttpSession session = request.getSession();
        requestAttributes.forEach(request::setAttribute);
        sessionAttributes.forEach(session::setAttribute);
    }

    @Override
    public String[] getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    @Override
    public Object getRequestAttribute(String key) {
        return requestAttributes.get(key);
    }

    @Override
    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }


    @Override
    public void setRequestAttribute(String key, Object attribute) {
        requestAttributes.put(key, attribute);
    }

    @Override
    public void setSessionAttribute(String key, Object attribute) {
        sessionAttributes.put(key, attribute);
    }

}
