package fernando.murta.wallet.model.transaction;


import fernando.murta.wallet.model.player.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "globalSequenceGenerator")
    @SequenceGenerator(name = "globalSequenceGenerator", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private UUID transaction;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @Column(columnDefinition = "DECIMAL(13,2)")
    private BigDecimal value;

    private TransactionType transactionType;

    @ManyToOne
    private Player player;
}
