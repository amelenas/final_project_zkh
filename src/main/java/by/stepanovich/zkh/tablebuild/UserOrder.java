package by.stepanovich.zkh.tablebuild;

import by.stepanovich.zkh.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class UserOrder extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(UserOrder.class);
    private static final long serialVersionUID = 11L;
    private static final String TABLE_BORDER = "</td><td>";
    private static final String TABLE_BORDER_SIZE = "<table border=2>";
    private static final String TOP_OF_TABLE = "<tr><th>User Id</th><th>User Name</th><th>User Surname</th>" +
            "<th>Phone</th><th>Email</th><th>User Status</th>" +
            "<th>Role</th></tr>";
    private ReportSet set;

    public UserOrder() {
    }

    public ReportSet getSet() {
        return set;
    }

    public void setSet(ReportSet set) {
        this.set = set;
    }

    @Override
    public int doStartTag() {
        ArrayList<User> users = new ArrayList<>();
        StringBuilder row;
        ArrayList<String> strings = new ArrayList<>();
       try {
            JspWriter out = pageContext.getOut();
            out.write(TABLE_BORDER_SIZE);
            out.write(TOP_OF_TABLE);
           for (Object objOrder : set.getSet()) {
               users.add((User) objOrder);
           }
           Collections.sort(users);
           for (User user : users) {
               row = new StringBuilder();
               row.append("<tr>");
               row.append("<td>");
               row.append(user.getUserId());
               row.append(TABLE_BORDER);
               row.append(user.getUserName());
               row.append(TABLE_BORDER);
               row.append(user.getUserSurname());
               row.append(TABLE_BORDER);
               row.append(user.getPhone());
               row.append(TABLE_BORDER);
               row.append(user.getEmail());
               row.append(TABLE_BORDER);
               row.append(user.getUserStatus());
               row.append(TABLE_BORDER);
               row.append(user.getRole());
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
