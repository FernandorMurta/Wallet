package fernando.murta.wallet.rest.player;

import fernando.murta.wallet.model.player.PlayerDTO;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
public interface PlayerService {

    /**
     * Method to create e new Player to use the Wallet
     * @param player Information about the Player to be created
     * @return All information if the Player was created
     */
    PlayerDTO createPlayer(PlayerDTO player);
}
