package fernando.murta.wallet.rest.transaction;

import fernando.murta.wallet.exceptions.EnoughFundsException;
import fernando.murta.wallet.exceptions.InvalidArgumentsException;
import fernando.murta.wallet.exceptions.InvalidTransactionTypeException;
import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.model.transaction.TransactionDTO;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
public interface TransactionService {

    /**
     * Method to create a Transaction and Debit credit from one Player.
     * To the method work's the TransactionType of the TransactionDTO need's to be DEBIT (from TransactionType Enum) or Null
     * if the TransactionType was CREDIT the method will throw an Exception
     *
     * @param idOfPlayer     Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from the Player.
     * @return The transaction Information after the execution
     * @throws InvalidTransactionTypeException if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException         If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException       If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException            If the Value sent to the transaction was bigger then the balance of the Player
     * @see fernando.murta.wallet.model.transaction.TransactionType For information about the enum
     */
    TransactionDTO debitValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException, PlayerNotFoundException, EnoughFundsException;


    /**
     * Method to create a Transaction and Debit credit from one Player.
     * To the method work's the TransactionType of the TransactionDTO need's to be CREDIT (from TransactionType Enum) or Null
     * if the TransactionType was DEBIT the method will throw an Exception
     *
     * @param idOfPlayer     Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from the Player.
     * @return The transaction Information after the execution
     * @throws InvalidTransactionTypeException if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException         If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException       If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @see fernando.murta.wallet.model.transaction.TransactionType For information about the enum
     */
    TransactionDTO creditValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException, PlayerNotFoundException;
}
