package by.stepanovich.zkh.connection;

import by.stepanovich.zkh.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Locale;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
           LOGGER.error("Exception when initializing pool data ", e);
        }
        Locale.setDefault(Locale.US);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().closeConnection();
        } catch (SQLException e) {
            LOGGER.error("Exception when destroying pool data ", e);
        }
    }
}
