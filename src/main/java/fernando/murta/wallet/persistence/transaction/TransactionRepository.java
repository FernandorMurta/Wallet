package fernando.murta.wallet.persistence.transaction;

import fernando.murta.wallet.model.player.Player;
import fernando.murta.wallet.model.transaction.Transaction;
import fernando.murta.wallet.model.transaction.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find one Transaction by the Transaction ID(UUID Transaction of the entity)
     *
     * @param uuid UUID value to find a Transaction
     * @return If one Transaction was found.
     */
    Optional<Transaction> findByTransaction(UUID uuid);

    /**
     * Method to list the Transactions of a Player, using the Player as parameter and TransactionType
     *
     * @param playerId        The player with a ID
     * @param transactionType The type of TransactionType will be used as parameter
     * @param pageable        Pageable with Page, Quantity and Sort information to list
     * @return A Page List with the Transactions of the Player
     */
    Page<Transaction> findAllByPlayerAndTransactionTypeIn(Player playerId, List<TransactionType> transactionType, Pageable pageable);
}
