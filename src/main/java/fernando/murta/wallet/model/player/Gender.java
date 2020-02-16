package fernando.murta.wallet.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Getter
@AllArgsConstructor
public enum Gender {

    MALE(0L, "MALE"),
    FEMALE(1L, "FEMALE");


    private Long id;

    private String gender;

}
