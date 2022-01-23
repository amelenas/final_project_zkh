package by.stepanovich.zkh.command.page;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ShowMainPageCommand implements Command {
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();
    public static final String PICTURES = "pictures";
    public static final String CURRENT_PAGE = "current_page";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(RequestContent request) {
        try {
            Map<String, String> photos = ORDER_SERVICE.extractPhotos();

            Map<String, String> result = new HashMap<>();
            for (Map.Entry<String, String> entry : photos.entrySet()) {

                String contextPath = "image/"+entry.getKey();
                result.put(contextPath, entry.getValue());
            }
            request.setRequestAttribute(PICTURES, result);


        } catch (ServiceException e) {
            request.setRequestAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setSessionAttribute(CURRENT_PAGE, PathOfJsp.SHOW_MAIN_PAGE);

        return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }

}
