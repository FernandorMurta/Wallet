package fernando.murta.wallet.rest.transaction;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.exceptions.EnoughFundsException;
import fernando.murta.wallet.exceptions.InvalidArgumentsException;
import fernando.murta.wallet.exceptions.InvalidTransactionTypeException;
import fernando.murta.wallet.exceptions.NotUniqueTransactionUUIDException;
import fernando.murta.wallet.model.player.Player;
import fernando.murta.wallet.model.player.PlayerDTO;
import fernando.murta.wallet.model.transaction.Transaction;
import fernando.murta.wallet.model.transaction.TransactionDTO;
import fernando.murta.wallet.model.transaction.TransactionType;
import fernando.murta.wallet.persistence.transaction.TransactionRepository;
import fernando.murta.wallet.rest.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private PlayerService playerService;

    /**
     * Constructor to D.I
     *
     * @param transactionRepository Transaction Repository
     * @param playerService         Player Service
     */
    @Autowired
    TransactionServiceImpl(TransactionRepository transactionRepository,
                           PlayerService playerService) {
        this.transactionRepository = transactionRepository;
        this.playerService = playerService;
    }

    /**
     * Method to create a Transaction and Debit credit from one Player.
     * To the method work's the TransactionType of the TransactionDTO need's to be DEBIT (from TransactionType Enum)
     * if the TransactionType was CREDIT or NULL the method will throw an Exception
     *
     * @param idOfPlayer     Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from the Player.
     * @return The transaction Information after the execution
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     * @see TransactionType For information about the enum
     */
    public TransactionDTO debitValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException,
            PlayerNotFoundException, EnoughFundsException, NotUniqueTransactionUUIDException {

        this.verifyTransactionType(TransactionType.DEBIT, transactionDTO);
        this.verifyUser(idOfPlayer, transactionDTO);

        if (transactionDTO.getPlayer().getBalance().subtract(transactionDTO.getValue()).doubleValue() < 0.0) {
            throw new EnoughFundsException();
        }

        this.verifyTransactionCode(transactionDTO);

        transactionDTO = TransactionDTO.fromEntity(this.transactionRepository.save(TransactionDTO.toEntity(transactionDTO)));

        this.updatePlayerAfterTransaction(transactionDTO);

        return transactionDTO;
    }

    /**
     * Method to create a Transaction and Debit credit from one Player.
     * To the method work's the TransactionType of the TransactionDTO need's to be CREDIT (from TransactionType Enum)
     * if the TransactionType was DEBIT or NULL the method will throw an Exception
     *
     * @param idOfPlayer     Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from the Player.
     * @return The transaction Information after the execution
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     * @see TransactionType For information about the enum
     */
    public TransactionDTO creditValueOfPlayer(Long idOfPlayer, TransactionDTO transactionDTO)
            throws InvalidTransactionTypeException, InvalidArgumentsException,
            PlayerNotFoundException, NotUniqueTransactionUUIDException {

        this.verifyTransactionType(TransactionType.CREDIT, transactionDTO);
        this.verifyUser(idOfPlayer, transactionDTO);
        this.verifyTransactionCode(transactionDTO);

        transactionDTO = TransactionDTO.fromEntity(this.transactionRepository.save(TransactionDTO.toEntity(transactionDTO)));

        this.updatePlayerAfterTransaction(transactionDTO);

        return transactionDTO;
    }

    /**
     * Method to list the Transactions of a Player, using the Player as parameter and TransactionType
     *
     * @param playerId        The player with a ID
     * @param transactionType The list with what type of TransactionType will be used as parameter
     * @param pageable        Pageable with Page, Quantity and Sort information to list
     * @return A Page List with the Transactions of the Player
     * @throws PlayerNotFoundException if the Id of Player sent dont find any player at the system
     */
    public Page<Transaction> findAllWithParameters(Long playerId, List<TransactionType> transactionType, Pageable pageable)
            throws PlayerNotFoundException {

        Player player = PlayerDTO.toEntity(this.playerService.findPlayerById(playerId));

        return this.transactionRepository.findAllByPlayerAndTransactionTypeIn(player, transactionType, pageable);
    }


    /**
     * Method to verify if the Type of Transaction in the request was the same accepted from the method
     *
     * @param acceptedType        The type accepted to the Transaction
     * @param receivedTransaction The Transaction, with the TransactionType to verify
     * @throws InvalidTransactionTypeException if the Type from Transaction was different from the acceptedType
     */
    private void verifyTransactionType(TransactionType acceptedType,
                                       TransactionDTO receivedTransaction) throws InvalidTransactionTypeException {
        if (!receivedTransaction.getTransactionType().equals(acceptedType)) {
            throw new InvalidTransactionTypeException(
                    new Throwable("Received " + receivedTransaction.getTransactionType().getType())
                            + ", but are expected " + acceptedType.getType() + " to this transaction!");
        }
    }

    /**
     * The method checks whether the ID sent in the parameter was the same of the player in transaction's DTO.
     * If the transaction has not been defined with a player ID or the parameter is the same as the transaction,
     * it finds a player by the ID received in the Parameter
     *
     * @param id             ID in the Parameter
     * @param transactionDTO he Transaction, with the Player to verify
     * @throws PlayerNotFoundException   If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     */
    private void verifyUser(Long id, TransactionDTO transactionDTO) throws PlayerNotFoundException, InvalidArgumentsException {
        if (transactionDTO.getPlayer() != null && !id.equals(transactionDTO.getPlayer().getId())) {
            throw new InvalidArgumentsException("Player ID in Parameter was different of the received from the transaction ");
        } else {
            transactionDTO.setPlayer(this.playerService.findPlayerById(id));
        }
    }

    /**
     * Method to update a Player after a transaction was saved
     *
     * @param transactionDTO Transaction info to update the player
     */
    private void updatePlayerAfterTransaction(TransactionDTO transactionDTO) {

        if (transactionDTO.getTransactionType().equals(TransactionType.DEBIT)) {

            transactionDTO.getPlayer().setBalance(transactionDTO.getPlayer().getBalance().subtract(transactionDTO.getValue()));

        } else if (transactionDTO.getTransactionType().equals(TransactionType.CREDIT)) {

            transactionDTO.getPlayer().setBalance(transactionDTO.getPlayer().getBalance().add(transactionDTO.getValue()));

        }

        transactionDTO.setPlayer(this.playerService.updatePlayer(transactionDTO.getPlayer()));
    }

    /**
     * Method to verify if the Transaction sent in the Request is a UNIQUE value
     *
     * @param transactionDTO Transaction Information sent
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    private void verifyTransactionCode(TransactionDTO transactionDTO) throws NotUniqueTransactionUUIDException {
        Optional<Transaction> transaction = this.transactionRepository.findByTransaction(transactionDTO.getTransaction());

        if (transaction.isPresent()) {
            throw new NotUniqueTransactionUUIDException();
        }
    }
}
