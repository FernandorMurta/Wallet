package fernando.murta.wallet.rest.transaction;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.exceptions.EnoughFundsException;
import fernando.murta.wallet.exceptions.InvalidArgumentsException;
import fernando.murta.wallet.exceptions.InvalidTransactionTypeException;
import fernando.murta.wallet.exceptions.NotUniqueTransactionUUIDException;
import fernando.murta.wallet.model.transaction.Transaction;
import fernando.murta.wallet.model.transaction.TransactionDTO;
import fernando.murta.wallet.model.transaction.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     * @see fernando.murta.wallet.model.transaction.TransactionType For information about the enum
     */
    TransactionDTO debitValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException,
            PlayerNotFoundException, EnoughFundsException, NotUniqueTransactionUUIDException;


    /**
     * Method to create a Transaction and Debit credit from one Player.
     * To the method work's the TransactionType of the TransactionDTO need's to be CREDIT (from TransactionType Enum) or Null
     * if the TransactionType was DEBIT the method will throw an Exception
     *
     * @param idOfPlayer     Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from the Player.
     * @return The transaction Information after the execution
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     * @see fernando.murta.wallet.model.transaction.TransactionType For information about the enum
     */
    TransactionDTO creditValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException,
            PlayerNotFoundException, NotUniqueTransactionUUIDException;


    /**
     * Method to list the Transactions of a Player, using the Player as parameter and TransactionType
     *
     * @param playerId        The player with a ID
     * @param transactionType The type of TransactionType will be used as parameter
     * @param pageable        Pageable with Page, Quantity and Sort information to list
     * @return A Page List with the Transactions of the Player
     * @throws PlayerNotFoundException if the Id of Player sent dont find any player at the system
     */
    Page<Transaction> findAllWithParameters(Long playerId, List<TransactionType> transactionType, Pageable pageable)
            throws PlayerNotFoundException;
}
