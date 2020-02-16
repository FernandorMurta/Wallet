package fernando.murta.wallet.persistence.transaction;

import fernando.murta.wallet.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
