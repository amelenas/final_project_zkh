package by.stepanovich.zkh.tablebuild.exception;

public class TableBuildException extends Exception{
    public TableBuildException() {
        super();
    }

    public TableBuildException(String message) {
        super(message);
    }

    public TableBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableBuildException(Throwable cause) {
        super(cause);
    }
}
