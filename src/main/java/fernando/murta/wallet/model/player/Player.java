package fernando.murta.wallet.model.player;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "globalSequenceGenerator")
    @SequenceGenerator(name = "globalSequenceGenerator", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    private String name;

    private Gender gender;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Column(columnDefinition = "DECIMAL(13,2) default 00.00")
    private BigDecimal balance;


    /**
     * Constructor with information of Parent Class
     *
     * @param id       Id of the Player
     * @param createAt Time of the Player was created
     * @param updateAt Time of the last update on Player
     * @param name     Name of the Player
     * @param gender   Gender of the Player
     * @param balance  Total value in the Wallet of the Player
     */
    @Builder
    Player(Long id, Date createAt, Date updateAt, String name, Gender gender, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
        this.balance = balance;
    }
}
