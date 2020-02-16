package fernando.murta.wallet.exceptions;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public class InvalidArgumentsException extends Exception {

    private static final String _DEFAULT_MSG = "Invalid Arguments!";

    /**
     * Constructor
     */
    public InvalidArgumentsException() {
        super(_DEFAULT_MSG);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     */
    public InvalidArgumentsException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     * @param error   Caused Error
     */
    public InvalidArgumentsException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor
     *
     * @param error Caused Error
     */
    public InvalidArgumentsException(Throwable error) {
        super(_DEFAULT_MSG, error);
    }
}
