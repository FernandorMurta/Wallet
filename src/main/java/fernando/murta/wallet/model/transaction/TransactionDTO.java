package fernando.murta.wallet.model.transaction;

import fernando.murta.wallet.core.AbstractDTO;
import fernando.murta.wallet.model.player.Player;
import fernando.murta.wallet.model.player.PlayerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionDTO extends AbstractDTO {

    @NotNull(message = "The Transaction Code is Required")
    private UUID transaction;

    @NotNull(message = "The value of the Transaction is Required")
    @Digits(integer = 9, fraction = 2, message = "Invalid Format of Balance. Limit of Fraction Value is 2")
    @DecimalMin(value = "0.0", inclusive = false, message = "The value of the Transaction must be more than 0")
    private BigDecimal value;

    @NotNull(message = "The Transaction Type is Required")
    private TransactionType transactionType;

    private PlayerDTO player;

    /**
     * Constructor with information of Parent Class
     *
     * @param id              Id of the Transaction in the Database
     * @param createdAt       Time of the Transaction was created
     * @param transaction     Transaction ID
     * @param transactionType TransactionType of the Transaction
     * @param value           Total value of the Transaction
     * @param player          Player involved with that transaction
     */
    @Builder
    TransactionDTO(Long id, Date createdAt, UUID transaction, TransactionType transactionType, BigDecimal value, Player player) {
        super(id, createdAt);
        this.transaction = transaction;
        this.transactionType = transactionType;
        this.value = value;
        this.player = PlayerDTO.fromEntity(player);
    }

    /**
     * Method to transform a Transaction DTO to a Entity
     *
     * @param transactionDTO Transaction Info
     * @return A Transaction Entity
     */
    public static Transaction toEntity(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .createdAt(transactionDTO.getCreatedAt())
                .transaction(transactionDTO.getTransaction())
                .transactionType(transactionDTO.getTransactionType())
                .value(transactionDTO.getValue())
                .player(PlayerDTO.toEntity(transactionDTO.getPlayer()))
                .build();
    }

    /**
     * Method to transform a Transaction Entity to a DTO
     *
     * @param transaction Transaction Entity
     * @return DTO of Transaction
     */
    public static TransactionDTO fromEntity(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .createdAt(transaction.getCreatedAt())
                .transaction(transaction.getTransaction())
                .transactionType(transaction.getTransactionType())
                .value(transaction.getValue())
                .player(transaction.getPlayer())
                .build();
    }
}
