package by.stepanovich.zkh.command.manager;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.PathOfJsp;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.exception.ServiceException;
import by.stepanovich.zkh.service.factory.ServiceFactory;
import by.stepanovich.zkh.tablebuild.ReportSet;

import javax.servlet.http.HttpServletRequest;

public class ShowAllOrdersCommand implements Command {
    private OrderService ORDER_SERVICE = ServiceFactory.getInstance().getOrderService();
    public static final String EXCEPTION = "exception";
    public static final String ORDER_BEAN = "oderBean";


    @Override
    public ResponseContext execute(HttpServletRequest request) {
        ReportSet reportSet;
        try {
            reportSet = new ReportSet(ORDER_SERVICE.findAllOrders());
            request.setAttribute(ORDER_BEAN, reportSet);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            return new ResponseContext(PathOfJsp.ERROR_500_PAGE, ResponseContext.ResponseContextType.FORWARD);
        }
        return new ResponseContext(PathOfJsp.BUILD_ORDER_REPORT, ResponseContext.ResponseContextType.FORWARD);

    }
}
