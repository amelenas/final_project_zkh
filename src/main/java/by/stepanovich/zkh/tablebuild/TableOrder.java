package by.stepanovich.zkh.tablebuild;

import by.stepanovich.zkh.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TableOrder extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(TableOrder.class);
    private static final long serialVersionUID = 11L;
    private ReportSet set;

    public TableOrder() {
    }

    public ReportSet getSet() {
        return set;
    }

    public void setSet(ReportSet set) {
        this.set = set;
    }

    @Override
    public int doStartTag() {
        ArrayList<Order> orders = new ArrayList<>();
        StringBuilder row;
        ArrayList<String> strings = new ArrayList<>();
       try {
            JspWriter out = pageContext.getOut();
            out.write("<table border=2>");
            out.write("<tr><th>Registration Id</th><th>User Id</th><th>Street</th>" +
                    "<th>House Number</th><th>Scope Of Work</th><th>Desirable Time</th><th>Opening Date</th>" +
                    "<th>Closing Date</th><th>Order Status</th><th>Additional Information</th>" +
                    "<th>Is Private}</th><th>Mark</th><th></th></tr>");
           for (Object objOrder : set.getSet()) {
               orders.add((Order) objOrder);
           }
           Collections.sort(orders);
           for (Order order : orders) {
               row = new StringBuilder();
               row.append("<tr>");
               row.append("<td>");
               row.append(order.getRegistrationId());
               row.append("</td><td>");
               row.append(order.getUserId());
               row.append("</td><td>");
               row.append(order.getStreet());
               row.append("</td><td>");
               row.append(order.getHouseNumber());
               row.append("</td><td>");
               row.append(order.getScopeOfWork());
               row.append("</td><td>");
               row.append(order.getDesirableTime());
               row.append("</td><td>");
               row.append(order.getOpeningDate());
               row.append("</td><td>");
               row.append(order.getClosingDate());
               row.append("</td><td>");
               row.append(order.getOrderStatus());
               row.append("</td><td>");
               row.append(order.getAdditionalInformation());
               row.append("</td><td>");
               row.append(order.isPrivate());
               row.append("</td>");
               row.append("</td><td>");
               row.append(order.getMark());
               row.append("</td>");
               row.append("</tr>");
               strings.add(row.toString());
           }
               for (String string : strings) {
                   out.write(string);
               }
               out.write("</table>");
               out.close();
        } catch (IOException e) {
           LOGGER.error("Error when building table", e);
        }
        return SKIP_BODY;
    }
}
