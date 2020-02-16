package fernando.murta.wallet.persistence.transaction;

import fernando.murta.wallet.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
