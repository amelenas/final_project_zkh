package by.stepanovich.zkh.command.controller;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.SessionRequestContentFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controller")

public class ApplicationController extends HttpServlet {

    private static final String COMMAND_PARAMETER_NAME = "command";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doAction(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doAction(req, resp);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        Command command = Command.of(commandName);
        RequestContent sessionRequestContent = SessionRequestContentFactory.defineContent(request);
        ResponseContext responseContext = command.execute(sessionRequestContent);
        sessionRequestContent.insertAttributes(request);
        if (responseContext.getResponseContextType().equals(ResponseContext.ResponseContextType.REDIRECT)) {
            response.sendRedirect(request.getContextPath() + responseContext.getPagePath());
        } else {
            request.getRequestDispatcher(responseContext.getPagePath()).forward(request, response);
        }
    }
}
