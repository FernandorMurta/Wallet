package fernando.murta.wallet.persistence.player;

import fernando.murta.wallet.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long> {
}
