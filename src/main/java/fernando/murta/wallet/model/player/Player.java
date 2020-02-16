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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
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
@Builder
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "globalSequenceGenerator")
    @SequenceGenerator(name = "globalSequenceGenerator", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Column(columnDefinition = "DECIMAL(13,2) default 00.00")
    private BigDecimal balance;
}
