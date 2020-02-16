package fernando.murta.wallet.rest.transaction;

import fernando.murta.wallet.model.transaction.Transaction;
import fernando.murta.wallet.model.transaction.TransactionDTO;
import fernando.murta.wallet.model.transaction.TransactionType;
import fernando.murta.wallet.util.LoggerMessage;
import fernando.murta.wallet.util.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

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
    @PostMapping(value = "/debit", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Response<TransactionDTO>> debitValueOfPlayer(
            @RequestBody @Valid TransactionDTO transactionDTO,
            @RequestParam(value = "player") Long id) {

        LoggerMessage.info(LoggerMessage._REQUEST_BODY, transactionDTO);
        LoggerMessage.params(
                Collections.singletonList(
                        new LoggerMessage.Params("Player ID", id.toString())));

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
    @PostMapping(value = "/credit", produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Response<TransactionDTO>> creditValueOfPlayer(
            @RequestBody @Valid TransactionDTO transactionDTO,
            @RequestParam(value = "player") Long id) {

        LoggerMessage.info(LoggerMessage._REQUEST_BODY, transactionDTO);
        LoggerMessage.params(
                Collections.singletonList(
                        new LoggerMessage.Params("Player ID", id.toString())));
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response<>(this.transactionService.creditValueOfPlayer(id, transactionDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    /**
     * Method to list the Transactions of a Player, using the Player as parameter and TransactionType.
     *
     * @param playerId        The player with a ID
     * @param transactionType The type of TransactionType will be used as parameter
     * @param page            Number of the page to request
     * @param qtd             Value of the amount of registers come from the request
     * @return A Page List with the Transactions of the Player
     */
    @ApiOperation(value = "Return a list of Transactions made by a Player")
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Response<Page<Transaction>>> findAllWithParameters(
            @RequestParam(value = "player") Long playerId,
            @RequestParam(value = "transactionType") List<TransactionType> transactionType,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "qtd", defaultValue = "10") Integer qtd) {

        LoggerMessage.params(
                Arrays.asList(
                        new LoggerMessage.Params("Player ID", playerId.toString()),
                        new LoggerMessage.Params("Page", page.toString()),
                        new LoggerMessage.Params("qtd", qtd.toString()),
                        new LoggerMessage.Params("Transactions Type", Optional.ofNullable(transactionType).isPresent()
                                ? transactionType
                                : new ArrayList<>())));

        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response<>(this.transactionService.findAllWithParameters(
                            playerId,
                            transactionType,
                            PageRequest.of(page, qtd))));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
