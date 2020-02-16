package fernando.murta.wallet.exceptions;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public class InvalidTransactionTypeException extends Exception {

    private static final String _DEFAULT_MSG = "Invalid Transaction Type!";

    /**
     * Constructor
     */
    public InvalidTransactionTypeException() {
        super(_DEFAULT_MSG);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     */
    public InvalidTransactionTypeException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     * @param error   Caused Error
     */
    public InvalidTransactionTypeException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor
     *
     * @param error Caused Error
     */
    public InvalidTransactionTypeException(Throwable error) {
        super(_DEFAULT_MSG, error);
    }
}
