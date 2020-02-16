package fernando.murta.wallet.rest.player;

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
}
