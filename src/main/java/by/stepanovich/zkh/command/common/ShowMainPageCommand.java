package by.stepanovich.zkh.command.common;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Role;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ShowMainPageCommand implements Command {
    private OrderService ORDER_SERVICE = ServiceFactory.getInstance().getOrderService();
    private static final String PICTURES = "pictures";
    private static final String CURRENT_PAGE = "current_page";
    private static final String EXCEPTION = "exception";
    private static final String ROLE = "role";
    private static final String IMAGE_TAG = "image/";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            Map<String, String> photos = ORDER_SERVICE.extractPhotos();

            Map<String, String> result = new HashMap<>();
            for (Map.Entry<String, String> entry : photos.entrySet()) {

                String contextPath = IMAGE_TAG+entry.getKey();
                result.put(contextPath, entry.getValue());
            }
            request.setAttribute(PICTURES, result);
            request.setAttribute(ROLE, Role.UNREGISTERED);

        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        session.setAttribute(CURRENT_PAGE, PathOfJsp.SHOW_MAIN_PAGE_CONTROLLER);

        return new ResponseContext(PathOfJsp.SHOW_MAIN_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }

}
