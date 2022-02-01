package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ShowMainPageCommand implements Command {
    private static final OrderService ORDER_SERVICE = ServiceFactory.getInstance().getOrderService();
    public static final String PICTURES = "pictures";
    public static final String CURRENT_PAGE = "current_page";
    public static final String EXCEPTION = "exception";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            Map<String, String> photos = ORDER_SERVICE.extractPhotos();

            Map<String, String> result = new HashMap<>();
            for (Map.Entry<String, String> entry : photos.entrySet()) {

                String contextPath = "image/"+entry.getKey();
                result.put(contextPath, entry.getValue());
            }
            request.setAttribute(PICTURES, result);


        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER);

        return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }

}
