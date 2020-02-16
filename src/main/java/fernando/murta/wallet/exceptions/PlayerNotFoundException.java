package fernando.murta.wallet.exceptions;

import fernando.murta.wallet.core.CoreException;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public class PlayerNotFoundException extends CoreException {

    private static final String _DEFAULT_MSG = "Player not found with that ID!";

    /**
     * Constructor
     */
    public PlayerNotFoundException() {
        super(_DEFAULT_MSG);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     */
    public PlayerNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param message Message from error
     * @param error   Caused Error
     */
    public PlayerNotFoundException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor
     *
     * @param error Caused Error
     */
    public PlayerNotFoundException(Throwable error) {
        super(_DEFAULT_MSG, error);
    }
}
