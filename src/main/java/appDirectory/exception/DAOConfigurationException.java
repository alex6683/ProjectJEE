package appDirectory.exception;

/**
 * Classe d'Exception relative aux configrations de la DAO
 *
 * @author Mestrallet Alexis
 * @author Risch Philippe
 *
 * @date 19/10/2017
 * @version 2.0
 */
public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}