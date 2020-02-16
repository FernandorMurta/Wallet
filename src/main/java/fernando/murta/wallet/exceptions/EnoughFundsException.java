package fernando.murta.wallet.exceptions;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public class EnoughFundsException extends Exception {

    private static final String _DEFAULT_MSG = "Enough Funds to this operation!";

    /**
     * Constructor
     */
    public EnoughFundsException() {
        super(_DEFAULT_MSG);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     */
    public EnoughFundsException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     * @param error   Caused Error
     */
    public EnoughFundsException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor
     *
     * @param error Caused Error
     */
    public EnoughFundsException(Throwable error) {
        super(_DEFAULT_MSG, error);
    }
}
