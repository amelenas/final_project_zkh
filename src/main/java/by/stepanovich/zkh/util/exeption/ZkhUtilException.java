package by.stepanovich.zkh.util.exeption;

public class ZkhUtilException extends Exception{

    public ZkhUtilException() {
        super();
    }

    public ZkhUtilException(String message) {
        super(message);
    }

    public ZkhUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZkhUtilException(Throwable cause) {
        super(cause);
    }
}
