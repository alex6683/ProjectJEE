package appDirectory.exception;

/**
 * Classe d'Exception relative aux mapper de la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 2.0
 */
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
