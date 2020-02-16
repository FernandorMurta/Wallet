package fernando.murta.wallet.model.player;

import fernando.murta.wallet.core.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerDTO extends AbstractDTO {

    private String name;

    private Gender gender;

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
    PlayerDTO(Long id, Date createAt, Date updateAt, String name, Gender gender, BigDecimal balance) {
        super(id, createAt, updateAt);
        this.name = name;
        this.gender = gender;
        this.balance = balance;
    }

    /**
     * Method to transform a Player Entity to a PlayerDTO
     *
     * @param player Player Entity
     * @return PlayerDTO with information of the Entity
     */
    public static PlayerDTO fromEntity(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .createAt(player.getCreatedAt())
                .updateAt(player.getUpdatedAt())
                .name(player.getName())
                .gender(player.getGender())
                .balance(player.getBalance())
                .build();
    }

    /**
     * Method to transform a PlayerDTO to a Player Entity
     *
     * @param playerDTO Player information in DTO class
     * @return Player Entity
     */
    public static Player toEntity(PlayerDTO playerDTO) {
        return Player.builder()
                .id(playerDTO.getId())
                .createAt(playerDTO.getCreatedAt())
                .updateAt(playerDTO.getUpdatedAt())
                .name(playerDTO.getName())
                .gender(playerDTO.getGender())
                .balance(playerDTO.getBalance())
                .build();
    }
}
