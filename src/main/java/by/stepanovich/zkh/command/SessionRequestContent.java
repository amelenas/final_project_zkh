package by.stepanovich.zkh.command;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;
import org.apache.logging.log4j.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

public class SessionRequestContent implements RequestContent {
    private static final Logger LOGGER = LogManager.getLogger(SessionRequestContent.class);
    public static final String PHOTO = "photo";
    public static final String PROPERTY_NAME = "generalKeys";
    public static final String ADD_TO_PHOTO_NAME = "photoZKH";
    public static final String SAVE_DIRECTORY = "photo.storage";
    public static final String PHOTO_MESSAGE = "photo_message";
    public static final String PHOTO_SAVED = "Photo uploaded successfully";
    public static final String PHOTO_NOT_SAVED = "Error when uploading a photo";
    public static final String PHOTO_EXIST = "The photo has already been uploaded";

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

        if (ServletFileUpload.isMultipartContent(request)) {
            savePhoto(request, session);
        }

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

    private void savePhoto(HttpServletRequest request, HttpSession session) {
        ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY_NAME);
        List<FileItem> items;
        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                System.out.println(item);
                if (!item.isFormField() && item.getSize() != 0 &&
                    item.getFieldName().equalsIgnoreCase(PHOTO)) {
                    String fileName = item.getName();
                    StringBuffer fileNameForSaving = new StringBuffer(fileName);
                    fileNameForSaving.insert(0, ADD_TO_PHOTO_NAME);
                    item.write(new File(bundle.getString(SAVE_DIRECTORY) + File.separator + fileNameForSaving));
                    session.setAttribute(PHOTO, fileName);
                    session.setAttribute(PHOTO_MESSAGE, PHOTO_SAVED);
                }
            }
        } catch (FileExistsException ex) {
            session.setAttribute(PHOTO_MESSAGE, PHOTO_EXIST);
            LOGGER.error("The photo has already been uploaded", ex);
        } catch (Exception e) {
            session.setAttribute(PHOTO_MESSAGE, PHOTO_NOT_SAVED);
            LOGGER.error("Exception when trying to save photo", e);
        }
    }
}
