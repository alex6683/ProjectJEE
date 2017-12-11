package appDirectory.exception;

/**
 * Classe d'Exception relative à la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 2.0
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}