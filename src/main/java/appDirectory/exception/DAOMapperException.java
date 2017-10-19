package appDirectory.exception;

public class DAOMapperException extends DAOException {

    public DAOMapperException(String message) {
        super(message);
    }

    public DAOMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOMapperException(Throwable cause) {
        super(cause);
    }
}
