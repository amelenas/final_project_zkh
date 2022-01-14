package by.stepanovich.zkh.command;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContentFactory {

    private SessionRequestContentFactory() {
    }

    public static SessionRequestContent defineContent(HttpServletRequest request) {
        SessionRequestContent sessionRequestContent = new SessionRequestContent();
        sessionRequestContent.extractValues(request);
        return sessionRequestContent;
    }
}
