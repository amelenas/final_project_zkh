package by.stepanovich.zkh.connection;
import by.stepanovich.zkh.connection.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionBuilder {
    Connection retrieveConnection() throws ConnectionPoolException;
}
