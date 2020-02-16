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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

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
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response<PlayerDTO>> createPlayer(@RequestBody PlayerDTO player) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response<>(this.playerService.createPlayer(player)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    /**
     * Method to find a Player by the ID
     *
     * @param id of the Player
     * @return Info about that player if are found
     */
    @ApiOperation(value = "Find a Player by his ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Response<PlayerDTO>> findPlayerById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response<>(this.playerService.findPlayerById(id)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Method to return a Balance of a Player
     *
     * @param id Id of the Player
     * @return Balance of the Player with that ID
     */
    @ApiOperation(value = "Return the Balance of a Player")
    @GetMapping("/balance")
    public ResponseEntity<Response<BigDecimal>> getBalanceOfPlayer(@RequestParam(value = "player") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response<>(this.playerService.findPlayerById(id).getBalance()));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
