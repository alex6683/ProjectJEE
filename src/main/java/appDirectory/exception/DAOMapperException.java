package appDirectory.exception;

public class DAOMapperException extends DAOException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
