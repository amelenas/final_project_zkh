package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.tablebuild.ReportSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ShowAllOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllOrdersCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private static final String EXCEPTION = "exception";
    private static final String ORDER_BEAN = "oderBean";

    @Override
    public ResponseContext execute(HttpServletRequest request) {
        ReportSet reportSet;
        try {
            reportSet = new ReportSet(orderService.findAllOrders());
            request.setAttribute(ORDER_BEAN, reportSet);
        } catch (ServiceException e) {
            LOGGER.error("Exception when searching all orders ", e);
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.BUILD_ORDER_REPORT, ResponseContext.ResponseContextType.FORWARD);

    }
}
