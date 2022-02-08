package by.stepanovich.zkh.tablebuild;

import by.stepanovich.zkh.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TableOrder extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(TableOrder.class);
    private static final long serialVersionUID = 11L;
    private static final String TABLE_BORDER = "</td><td>";
    private static final String TABLE_BORDER_SIZE = "<table border=2>";
    private static final String TOP_OF_TABLE = "<tr><th>Registration Id</th><th>User Id</th><th>Street</th>" +
            "<th>House Number</th><th>Scope Of Work</th><th>Desirable Time</th><th>Opening Date</th>" +
            "<th>Closing Date</th><th>Order Status</th><th></th></tr>";
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
            out.write(TABLE_BORDER_SIZE);
            out.write(TOP_OF_TABLE);
           for (Object objOrder : set.getSet()) {
               orders.add((Order) objOrder);
           }
           Collections.sort(orders);
           for (Order order : orders) {
               row = new StringBuilder();
               row.append("<tr>");
               row.append("<td>");
               row.append(order.getRegistrationId());
               row.append(TABLE_BORDER);
               row.append(order.getUserId());
               row.append(TABLE_BORDER);
               row.append(order.getStreet());
               row.append(TABLE_BORDER);
               row.append(order.getHouseNumber());
               row.append(TABLE_BORDER);
               row.append(order.getScopeOfWork());
               row.append(TABLE_BORDER);
               row.append(order.getDesirableTime());
               row.append(TABLE_BORDER);
               row.append(order.getOpeningDate());
               row.append(TABLE_BORDER);
               row.append(order.getClosingDate());
               row.append(TABLE_BORDER);
               row.append(order.getOrderStatus());
               row.append(TABLE_BORDER);
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
