package fernando.murta.wallet.exceptions;

import fernando.murta.wallet.core.CoreException;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public class NotUniqueTransactionUUIDException extends CoreException {

    private static final String _DEFAULT_MSG = "That UUID already have one Transaction registered!";

    /**
     * Constructor
     */
    public NotUniqueTransactionUUIDException() {
        super(_DEFAULT_MSG);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     */
    public NotUniqueTransactionUUIDException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     * @param error   Caused Error
     */
    public NotUniqueTransactionUUIDException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor
     *
     * @param error Caused Error
     */
    public NotUniqueTransactionUUIDException(Throwable error) {
        super(_DEFAULT_MSG, error);
    }
}
