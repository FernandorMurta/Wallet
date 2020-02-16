package fernando.murta.wallet.rest.player;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.model.player.PlayerDTO;
import fernando.murta.wallet.persistence.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    /**
     * Constructor to D.I
     *
     * @param playerRepository Player Repository
     */
    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Method to create e new Player to use the Wallet
     *
     * @param player Information about the Player to be created
     * @return All information if the Player was created
     */
    public PlayerDTO createPlayer(PlayerDTO player) {
        player.setBalance(new BigDecimal("0.00"));
        return PlayerDTO.fromEntity(this.playerRepository.save(PlayerDTO.toEntity(player)));
    }

    /**
     * Method to find a Player by the ID
     *
     * @param id Id to find a Player
     * @return Player DTO founded with that ID
     * @throws PlayerNotFoundException If a Player was not found with the ID sent in parameter
     */
    public PlayerDTO findPlayerById(Long id) throws PlayerNotFoundException {
        return PlayerDTO.fromEntity(this.playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new));
    }

    /**
     * Method to Update a Player
     *
     * @param player Information about the Player to be updated
     * @return All information if the Player was updated
     */
    public PlayerDTO updatePlayer(PlayerDTO player) {
        return PlayerDTO.fromEntity(this.playerRepository.save(PlayerDTO.toEntity(player)));
    }
}
