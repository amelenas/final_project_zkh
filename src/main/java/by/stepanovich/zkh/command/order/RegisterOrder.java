package by.stepanovich.zkh.command.order;

import by.stepanovich.zkh.command.Command;
import by.stepanovich.zkh.command.Path;
import by.stepanovich.zkh.command.RequestContent;
import by.stepanovich.zkh.command.ResponseContext;
import by.stepanovich.zkh.command.page.ShowMainPageCommand;
import by.stepanovich.zkh.entity.Order;
import by.stepanovich.zkh.service.OrderService;
import by.stepanovich.zkh.service.impl.OrderServiceImpl;

import java.util.Optional;

public class RegisterOrder implements Command {

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_PROFILE_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();

    @Override
    public ResponseContext execute(RequestContent req) {
        Optional<Order> optionalUser = ORDER_SERVICE.registerOrder(Integer.parseInt(req.getRequestParameter("id")[0]),
                req.getRequestParameter("street")[0],
                req.getRequestParameter("houseNumber")[0], req.getRequestParameter("scopeOfWork")[0],
                req.getRequestParameter("desirableTimeOfWork")[0], " ");

        if (optionalUser.isPresent()) {
            //successfulMessage
        } else {
            //failedMessage
            return RESPONSE;
        }
        return new ShowMainPageCommand().execute(req);
    }
}
