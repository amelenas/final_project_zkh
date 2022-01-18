package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

import java.sql.Timestamp;
import java.util.Optional;

public class RegisterOrder implements Command {
    private static final String STREET = "street";
    private static final String USER_ID = "id";
    private static final String HOUSE_NUMBER = "houseNumber";
    private static final String SCOPE_OF_WORK = "scopeOfWork";
    private static final String DESIRABLE_TIME_OF_WORK = "desirableTimeOfWork";
    private static final String PHOTO = "photo";
    private static final String ADD_SECONDS = ":00";
    private static final String EMPTY_STRING = " ";
    private static final String LETTER_T = "T";


    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {
        StringBuilder desirableTime = new StringBuilder(req.getRequestParameter(DESIRABLE_TIME_OF_WORK)[0]);
        System.out.println(req.getSessionAttribute(PHOTO));
        if (desirableTime.isEmpty()){
            desirableTime.append(new Timestamp(System.currentTimeMillis()));
        } else {
            int charIndex = req.getRequestParameter(DESIRABLE_TIME_OF_WORK)[0].indexOf(LETTER_T);
            desirableTime.replace(charIndex, charIndex+1,EMPTY_STRING).append(ADD_SECONDS);
            System.out.println(desirableTime);
        }

        Optional<Order> optionalOrder = ORDER_SERVICE.registerOrder(Integer.parseInt(req.getSessionAttribute(USER_ID).toString()),
                req.getRequestParameter(STREET)[0],
                req.getRequestParameter(HOUSE_NUMBER)[0], req.getRequestParameter(SCOPE_OF_WORK)[0],
                desirableTime.toString(), String.valueOf(req.getSessionAttribute(PHOTO)));

        if (optionalOrder.isPresent()) {


        } else {
            //failedMessage
            //logger
            return new ResponseContext(Path.REQUEST_ACCEPTED, ResponseContext.ResponseContextType.FORWARD) ;
        }
        return new ShowMainPageCommand().execute(req);
    }
}
