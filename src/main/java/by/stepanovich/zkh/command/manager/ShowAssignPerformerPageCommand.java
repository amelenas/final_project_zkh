package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.entity.OrderStatus;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAssignPerformerPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAssignPerformerPageCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ORDER_DATA = "orderData";
    private static final String EXCEPTION = "exception";
    private static final String PAGE = "page";
    private static final String NO_OF_PAGES = "noOfPages";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        List<Order> newOrders;
        String page = request.getParameter(PAGE);
        int currentPage = page != null ? Integer.parseInt(page) : 1;
        int recordsPerPage = 10;
        try {
            List<Order> allNewOrders = orderService.findOrdersByStatus(OrderStatus.IN_PROCESSING);
            newOrders = orderService.findOrdersByStatus(OrderStatus.IN_PROCESSING, currentPage);
            int noOfRecords = (int) Math.ceil(allNewOrders.size() * 1.0/ recordsPerPage);

            request.setAttribute(NO_OF_PAGES, noOfRecords);
            request.setAttribute(PAGE, currentPage);

        } catch (ServiceException e) {
            LOGGER.error("Exception when searching all orders be status ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        request.setAttribute(ORDER_DATA, newOrders);

        return new ResponseContext(PathOfJsp.ASSIGN_PERFORMER_PAGE, ResponseContext.ResponseContextType.FORWARD);
    }
}
