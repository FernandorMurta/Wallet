package fernando.murta.wallet.rest.player;


import fernando.murta.wallet.model.player.PlayerDTO;
import fernando.murta.wallet.util.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = PlayerController._PATH)
public class PlayerController {

    static final String _PATH = "/player";

    private PlayerService playerService;

    /**
     * Constructor to D.I
     *
     * @param playerService Player Service Implementation class
     */
    @Autowired
    PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    /**
     * Endpoint to Create a new Player
     *
     * @param player Information of the new Player
     * @return new Player Created
     */
    @ApiOperation(value = "Generate a new Player to use the Wallet")
    @PostMapping
    public ResponseEntity<Response<PlayerDTO>> createPlayer(@RequestBody PlayerDTO player) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response<>(this.playerService.createPlayer(player)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
        }
    }
}
