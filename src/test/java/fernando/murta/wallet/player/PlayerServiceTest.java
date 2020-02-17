package fernando.murta.wallet.player;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.model.player.Gender;
import fernando.murta.wallet.model.player.Player;
import fernando.murta.wallet.model.player.PlayerDTO;
import fernando.murta.wallet.persistence.player.PlayerRepository;
import fernando.murta.wallet.rest.player.PlayerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Fernando Murta
 * @version 0.0.3
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {


    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;


    PlayerDTO playerDTO;

    /**
     * Method to build objects before the execution of the test
     */
    @Before
    public void init() {
        this.playerDTO = PlayerDTO.builder()
                .id(null)
                .name("Fernando Murta")
                .gender(Gender.MALE)
                .balance(new BigDecimal("0.00"))
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    /**
     * Method to test a creation of a new Player
     */
    @Test
    public void createNewPlayer() {

        Player player = PlayerDTO.toEntity(this.playerDTO);

        Mockito.when(this.playerRepository.save(any(Player.class)))
                .thenReturn(player);

        PlayerDTO playerDTO = this.playerService.createPlayer(this.playerDTO);

        Assert.assertNotNull(playerDTO);
        Assert.assertEquals(playerDTO, this.playerDTO);
    }

    /**
     * Method to find one Player by his ID
     * @throws PlayerNotFoundException If the ID received dont find any player
     */
    @Test
    public void findPlayerById() throws PlayerNotFoundException {
        Player player = PlayerDTO.toEntity(this.playerDTO);

        Mockito.when(this.playerRepository.findById(1L))
                .thenReturn(Optional.of(player));

        PlayerDTO playerDTO = this.playerService.findPlayerById(1L);
        Assert.assertNotNull(playerDTO);
        Assert.assertEquals(playerDTO, this.playerDTO);
    }

    /**
     * Method to update a Player in Database
     */
    @Test
    public void updateAPlayer() {
        Player player = PlayerDTO.toEntity(this.playerDTO);
        player.setBalance(new BigDecimal("1.00"));

        Mockito.when(this.playerRepository.save(any(Player.class)))
                .thenReturn(player);

        PlayerDTO playerDTO = this.playerService.createPlayer(this.playerDTO);

        Assert.assertNotNull(playerDTO);
        Assert.assertEquals(playerDTO.getBalance(), new BigDecimal("1.00"));
        Assert.assertNotEquals(playerDTO, this.playerDTO);
    }

    /**
     * Method to find a Player by his ID but will fail because the ID doesn't match with any registered player
     * @throws PlayerNotFoundException If a Player was not found with that id
     */
    @Test(expected = PlayerNotFoundException.class)
    public void findPlayerByIdButFailBecauseNoPlayerExistsWithThisId() throws PlayerNotFoundException {

        Mockito.when(this.playerRepository.findById(1L).orElseThrow(PlayerNotFoundException::new))
                .thenReturn(new Player());

        this.playerService.findPlayerById(1L);
    }
}
