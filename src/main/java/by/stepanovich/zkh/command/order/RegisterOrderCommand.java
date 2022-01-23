package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.user.ShowRegisterRequestPage;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterOrderCommand.class);
    public static final String PROPERTY_NAME = "generalKeys";
    private static final String STREET = "street";
    private static final String USER_ID = "id";
    private static final String HOUSE_NUMBER = "houseNumber";
    private static final String SCOPE_OF_WORK = "scopeOfWork";
    private static final String APARTMENT = "apartment";
    private static final String DESIRABLE_TIME_OF_WORK = "desirableTimeOfWork";
    private static final String PHOTO = "photo";
    public static final String CURRENT_PAGE = "current_page";
    public static final String ERROR_REGISTER_ORDER_MASSAGE = "errorRegisterOrderMessage";
    public static final String SUCCESS_REGISTER_ORDER_MASSAGE = "successRegisterOrderMessage";

    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {
        StringBuilder desirableTime = new StringBuilder(req.getRequestParameter(DESIRABLE_TIME_OF_WORK)[0]);
        ResourceBundle generalKeys = ResourceBundle.getBundle(PROPERTY_NAME);
        if (desirableTime.isEmpty()) {
            desirableTime.append(new Timestamp(System.currentTimeMillis()));
        } else {
            int charIndex = req.getRequestParameter(DESIRABLE_TIME_OF_WORK)[0].indexOf("T");
            desirableTime.replace(charIndex, charIndex + 1, " ").append(":00");
        }
        Optional<Order> optionalOrder = Optional.empty();
        try {
            optionalOrder = ORDER_SERVICE.registerOrder(Integer.parseInt(req.getSessionAttribute(USER_ID).toString()),
                    req.getRequestParameter(STREET)[0],
                    req.getRequestParameter(HOUSE_NUMBER)[0], req.getRequestParameter(APARTMENT)[0], req.getRequestParameter(SCOPE_OF_WORK)[0],
                    desirableTime.toString(), String.valueOf(req.getSessionAttribute(PHOTO)));
        } catch (ServiceException e) {
            LOGGER.error("Exception in RegisterOrder command", e);
        }

        if (optionalOrder.isEmpty()) {
            req.setSessionAttribute(ERROR_REGISTER_ORDER_MASSAGE, generalKeys.getString("message.registerOrder.error"));
            req.setSessionAttribute(CURRENT_PAGE, PathOfJsp.USER_REGISTER_ORDER);
            return new ShowRegisterRequestPage().execute(req);
        }
        req.setSessionAttribute(CURRENT_PAGE, PathOfJsp.SHOW_REQUEST_ACCEPTED);
        req.setSessionAttribute(SUCCESS_REGISTER_ORDER_MASSAGE, generalKeys.getString("message.registerOrder.success"));
        req.setSessionAttribute(PHOTO, " ");
        return new ResponseContext(PathOfJsp.SHOW_REQUEST_ACCEPTED, ResponseContext.ResponseContextType.FORWARD);
    }
}
