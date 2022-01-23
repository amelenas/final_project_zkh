package by.stepanovich.zkh.tablebuild;

import by.stepanovich.zkh.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class TableBuilder extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(TableBuilder.class);
    private static final long serialVersionUID = 11L;
    private OrderSet set;

    public TableBuilder() {
    }

    public OrderSet getSet() {
        return set;
    }

    public void setSet(OrderSet set) {
        this.set = set;
    }

    @Override
    public int doStartTag() {
        ArrayList<Order> orders = new ArrayList<>();
        StringBuilder row;
        ArrayList<String> strings = new ArrayList<>();
       try {
            JspWriter out = pageContext.getOut();
            out.write("<html>");
            out.write("<head>");
            out.write("<title>Servlet upload</title>");
            out.write("</head>");
            out.write("<body>");
            out.write("<div align = center>");
            out.write("Tariffs");
            out.write("</div>");
            out.write("<table border=2 name=Tariffs>");
            out.write("<tr><th>Tariff Name</th><th>Tariff ID</th><th>Operator</th>" +
                    "<th>Inner Call Price</th><th>Outer Call Price</th><th>Internet GB</th><th>Payroll</th>" +
                    "<th>SMS Price</th><th>Amount of Favorite Numbers</th><th>Tariffication</th>" +
                    "<th>After GB end 100MB Price</th></tr>");
           for (Object objOrder : set.getSet()) {
               orders.add((Order) objOrder);
           }
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
               out.write("<br><br>");
               out.write("<a href = parsers.jsp>BACK TO PARSERS</a></div>");
               out.write("<br><br>");
               out.write("<a href = index.jsp>BACK TO MAIN PAGE</a></div>");
               out.write("</body>");
               out.write("</html>");
               out.close();
        } catch (IOException e) {
           LOGGER.error("Error when building table", e);
        }
        return SKIP_BODY;
    }
}
