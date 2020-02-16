package fernando.murta.wallet.rest.transaction;

import fernando.murta.wallet.model.transaction.TransactionDTO;
import fernando.murta.wallet.util.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


/**
 * @author Fernando Murta
 * @version 0.0.2
 */
@RestController
@RequestMapping(value = TransactionController._PATH)
public class TransactionController {

    static final String _PATH = "/transaction";

    private TransactionService transactionService;


    /**
     * Constructor to D.I
     *
     * @param transactionService Transaction Service Implementation
     */
    @Autowired
    TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Endpoint to create a new Transaction to debit value from one player
     *
     * @param id             Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Debit from one Player.
     * @return The transaction Information after the execution
     */
    @ApiOperation(value = "Create a new Transaction to debit value from one player")
    @PostMapping(value = "/debit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response<TransactionDTO>> debitValueOfPlayer(
            @RequestBody @Valid TransactionDTO transactionDTO,
            @RequestParam(value = "player") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response<>(this.transactionService.debitValueOfPlayer(id, transactionDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Endpoint to create a new Transaction to Credit value to a player
     *
     * @param id             Id of the Player who will Debit the Credit
     * @param transactionDTO Information about the Transaction to Credit to a Player.
     * @return The transaction Information after the execution
     */
    @ApiOperation(value = "Create a new Transaction to Credit value to a player")
    @PostMapping(value = "/credit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Response<TransactionDTO>> creditValueOfPlayer(
            @RequestBody @Valid TransactionDTO transactionDTO,
            @RequestParam(value = "player") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response<>(this.transactionService.creditValueOfPlayer(id, transactionDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
