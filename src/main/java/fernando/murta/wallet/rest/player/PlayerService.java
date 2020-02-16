package fernando.murta.wallet.rest.player;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.model.player.PlayerDTO;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
public interface PlayerService {

    /**
     * Method to create e new Player to use the Wallet
     *
     * @param player Information about the Player to be created
     * @return All information if the Player was created
     */
    PlayerDTO createPlayer(PlayerDTO player);

    /**
     * Method to find a Player by the ID
     *
     * @param id Id to find a Player
     * @return Player DTO founded with that ID
     * @throws PlayerNotFoundException If a Player was not found with the ID sent in parameter
     */
    PlayerDTO findPlayerById(Long id) throws PlayerNotFoundException;


    /**
     * Method to Update a Player
     *
     * @param player Information about the Player to be updated
     * @return All information if the Player was updated
     */
    PlayerDTO updatePlayer(PlayerDTO player);
}
