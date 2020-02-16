package fernando.murta.wallet.model.transaction;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Getter
@AllArgsConstructor
public enum TransactionType {

    DEBIT(0L, "DEBIT"),
    CREDIT(1L, "CREDIT");

    private Long id;

    private String type;

}
