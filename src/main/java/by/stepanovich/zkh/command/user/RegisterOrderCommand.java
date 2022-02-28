package by.stepanovich.zkh.command.user;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

public class RegisterOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterOrderCommand.class);
    private static final String STREET = "street";
    private static final String USER_ID = "id";
    private static final String HOUSE_NUMBER = "houseNumber";
    private static final String SCOPE_OF_WORK = "scopeOfWork";
    private static final String APARTMENT = "apartment";
    private static final String DESIRABLE_TIME_OF_WORK = "desirableTimeOfWork";
    private static final String PHOTO = "photo";
    private static final String CURRENT_PAGE = "current_page";
    private static final String ERROR_REGISTER_ORDER_MASSAGE = "errorRegisterOrderMessage";
    private static final String SUCCESS_REGISTER_ORDER_MASSAGE = "successRegisterOrderMessage";
    private static final String ERROR_MESSAGE = "Error during registration. The text should not contain the < or > character";
    private static final String SUCCESS_MESSAGE = " Your order was registered successfully";
    private static final String PROPERTY_NAME = "generalKeys";
    private static final String SAVE_DIRECTORY = "photo.storage";
    private static final String PHOTO_MESSAGE = "photo_message";
    private static final String PHOTO_NOT_SAVED = "Error when uploading a photo";
    private static final String PHOTO_EXIST = "The photo has already been uploaded";
    private static final String ENCODING = "UTF-8";

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        Optional<Order> optionalOrder = Optional.empty();
        HttpSession session = request.getSession();
        StringBuilder fileNameForSaving = new StringBuilder();
        StringBuilder desirableTime = new StringBuilder();
        ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY_NAME);
        StringBuilder street = new StringBuilder();
        StringBuilder houseNumber = new StringBuilder();
        StringBuilder apartment = new StringBuilder();
        StringBuilder scopeOfWork = new StringBuilder();
        List<FileItem> items;
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String currentTime = timestamp.substring(0, timestamp.indexOf("."));
        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField() && item.getSize() != 0 &&
                        item.getFieldName().equalsIgnoreCase(PHOTO)) {

                    fileNameForSaving = new StringBuilder(item.getName());
                    fileNameForSaving.insert(0, currentTime.substring(0, timestamp.indexOf(" "))).insert(0, (int) (Math.random() * 100));
                    item.write(new File(bundle.getString(SAVE_DIRECTORY) + File.separator + fileNameForSaving));
                }

                if (item.getFieldName().equalsIgnoreCase(DESIRABLE_TIME_OF_WORK)) {
                    if (item.getSize() != 0){
                        desirableTime = new StringBuilder(IOUtils.toString(item.getInputStream(), ENCODING));
                        int charIndex = desirableTime.indexOf("T");
                        desirableTime.replace(charIndex, charIndex + 1, " ").append(":00");

                    } else {
                        desirableTime.append(timestamp);
                    }
                }
                if (item.getFieldName().equalsIgnoreCase(STREET)) {
                    street = new StringBuilder(IOUtils.toString(item.getInputStream(), ENCODING));
                }
                if (item.getFieldName().equalsIgnoreCase(HOUSE_NUMBER)) {
                    houseNumber = new StringBuilder(IOUtils.toString(item.getInputStream(), ENCODING));
                }
                if (item.getFieldName().equalsIgnoreCase(SCOPE_OF_WORK)) {
                    scopeOfWork = new StringBuilder(IOUtils.toString(item.getInputStream(), ENCODING));
                }
                if (item.getFieldName().equalsIgnoreCase(APARTMENT)) {
                    apartment = new StringBuilder(IOUtils.toString(item.getInputStream(), ENCODING));
                }
            }

        } catch (FileUploadException ex) {
            request.setAttribute(PHOTO_MESSAGE, PHOTO_EXIST);
            LOGGER.error("The photo has already been uploaded", ex);
        } catch (Exception e) {
            request.setAttribute(PHOTO_MESSAGE, PHOTO_NOT_SAVED);
            LOGGER.error("Exception when trying to save photo", e);
        }
        try {
            optionalOrder = orderService.registerOrder(Integer.parseInt(session.getAttribute(USER_ID).toString()),
                    String.valueOf(street), String.valueOf(houseNumber), String.valueOf(apartment), String.valueOf(scopeOfWork),
                    desirableTime.toString(), String.valueOf(fileNameForSaving));
        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterOrder command", e);
        }

        if (optionalOrder.isEmpty()) {
            request.setAttribute(ERROR_REGISTER_ORDER_MASSAGE, ERROR_MESSAGE);
            request.setAttribute(CURRENT_PAGE, PathOfJsp.USER_REGISTER_ORDER);
            return new ShowRegisterRequestPageCommand().execute(request);
        }
        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REQUEST_ACCEPTED_COMMAND);
        request.setAttribute(SUCCESS_REGISTER_ORDER_MASSAGE, SUCCESS_MESSAGE);
        return new ResponseContext(PathOfJsp.SHOW_REQUEST_ACCEPTED_COMMAND, ResponseContext.ResponseContextType.REDIRECT);
    }
}


