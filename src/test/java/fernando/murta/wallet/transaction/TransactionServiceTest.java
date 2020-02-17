package fernando.murta.wallet.transaction;

import fernando.murta.wallet.exceptions.PlayerNotFoundException;
import fernando.murta.wallet.exceptions.EnoughFundsException;
import fernando.murta.wallet.exceptions.InvalidArgumentsException;
import fernando.murta.wallet.exceptions.InvalidTransactionTypeException;
import fernando.murta.wallet.exceptions.NotUniqueTransactionUUIDException;
import fernando.murta.wallet.model.player.Gender;
import fernando.murta.wallet.model.player.Player;
import fernando.murta.wallet.model.player.PlayerDTO;
import fernando.murta.wallet.model.transaction.Transaction;
import fernando.murta.wallet.model.transaction.TransactionDTO;
import fernando.murta.wallet.model.transaction.TransactionType;
import fernando.murta.wallet.persistence.player.PlayerRepository;
import fernando.murta.wallet.persistence.transaction.TransactionRepository;
import fernando.murta.wallet.rest.player.PlayerService;
import fernando.murta.wallet.rest.transaction.TransactionServiceImpl;
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
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Fernando Murta
 * @version 0.0.3
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {


    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private PlayerService playerService;

    TransactionDTO transactionDTODebit;

    TransactionDTO transactionDTOCredit;

    Player player;


    /**
     * Method to build objects before the execution of the test
     */
    @Before
    public void init() {

        this.player = Player.builder()
                .id(1L)
                .name("Fernando Murta")
                .gender(Gender.MALE)
                .balance(new BigDecimal("0.00"))
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        this.transactionDTODebit = TransactionDTO.builder()
                .transaction(UUID.randomUUID())
                .transactionType(TransactionType.DEBIT)
                .value(new BigDecimal("50.00"))
                .createdAt(null)
                .id(null)
                .player(this.player)
                .build();

        this.transactionDTOCredit = TransactionDTO.builder()
                .transaction(UUID.randomUUID())
                .transactionType(TransactionType.CREDIT)
                .value(new BigDecimal("50.00"))
                .createdAt(null)
                .id(null)
                .player(this.player)
                .build();
    }

    /**
     * Method to test if a Credit Transaction was created
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test
    public void createCreditTransaction()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException {

        Mockito.when(this.transactionRepository.save(any(Transaction.class)))
                .thenReturn(TransactionDTO.toEntity(this.transactionDTOCredit));

        Mockito.when(this.playerService.findPlayerById(1L))
                .thenReturn(PlayerDTO.fromEntity(this.player));

        TransactionDTO transactionDTO =
                this.transactionService.creditValueOfPlayer(1L, this.transactionDTOCredit);

        Assert.assertNotNull(transactionDTO);
        Assert.assertEquals(transactionDTO.getTransactionType(), this.transactionDTOCredit.getTransactionType());
        Assert.assertEquals(transactionDTO.getTransaction(), this.transactionDTOCredit.getTransaction());
    }

    /**
     * Method to test if a Credit Transaction was created but will fail because the Type of the Transaction was wrong
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = InvalidTransactionTypeException.class)
    public void createCreditTransactionButFailBecauseTypeWasWrong()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException {

        this.transactionService.creditValueOfPlayer(1L, this.transactionDTODebit);
    }

    /**
     * Method to test if a Credit Transaction was created but will fail
     * because the ID of the Player was different of the Parameter received
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = InvalidArgumentsException.class)
    public void createCreditTransactionButFailBecauseIdFromPlayerWasDifferentOfParameter()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException {

        this.transactionService.creditValueOfPlayer(2L, this.transactionDTOCredit);
    }

    /**
     * Method to test if a Credit Transaction was created but will fail because the Player was not found with that ID
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = PlayerNotFoundException.class)
    public void createCreditTransactionButFailBecausePlayerDontExist()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException {

        Mockito.when(this.playerRepository.findById(1L).orElseThrow(PlayerNotFoundException::new))
                .thenReturn(new Player());

        this.transactionService.creditValueOfPlayer(1L, this.transactionDTOCredit);
    }

    /**
     * Method to test if a Credit Transaction was created but will fail because the UUID of transaction already exists
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = NotUniqueTransactionUUIDException.class)
    public void createCreditTransactionButFailBecauseUUIDofTransactionAlreadyExists()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException {


        Mockito.when(this.playerService.findPlayerById(1L))
                .thenReturn(PlayerDTO.fromEntity(this.player));

        Mockito.when(this.transactionRepository.findByTransaction(this.transactionDTOCredit.getTransaction()))
                .thenReturn(Optional.of(TransactionDTO.toEntity(this.transactionDTODebit)));

        this.transactionService.creditValueOfPlayer(1L, this.transactionDTOCredit);
    }

    /**
     * Method to test if a Debit Transaction was created
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test
    public void createDebitTransaction()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        Mockito.when(this.transactionRepository.save(any(Transaction.class)))
                .thenReturn(TransactionDTO.toEntity(this.transactionDTODebit));

        this.player.setBalance(new BigDecimal("60.00"));

        Mockito.when(this.playerService.findPlayerById(1L))
                .thenReturn(PlayerDTO.fromEntity(this.player));

        TransactionDTO transactionDTO =
                this.transactionService.debitValueOfPlayer(1L, this.transactionDTODebit);

        Assert.assertNotNull(transactionDTO);
        Assert.assertEquals(transactionDTO.getTransactionType(), this.transactionDTODebit.getTransactionType());
        Assert.assertEquals(transactionDTO.getTransaction(), this.transactionDTODebit.getTransaction());
    }

    /**
     * Method to test if a Debit Transaction was created but will fail because the Transaction Type was wrong
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = InvalidTransactionTypeException.class)
    public void createDebitTransactionButFailBecauseTypeWasWrong()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        this.transactionService.debitValueOfPlayer(1L, this.transactionDTOCredit);
    }

    /**
     * Method to test if a Debit Transaction was created but will fail
     * because the ID of the Player was different of the Parameter received
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = InvalidArgumentsException.class)
    public void createDebitTransactionButFailBecauseIdFromPlayerWasDifferentOfParameter()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        this.transactionService.debitValueOfPlayer(2L, this.transactionDTODebit);
    }

    /**
     * Method to test if a Debit Transaction was created but will fail because the Player was not found with that ID
     * because the ID of the Player was different of the Parameter received
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = PlayerNotFoundException.class)
    public void createDebitTransactionButFailBecausePlayerDontExist()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        Mockito.when(this.playerRepository.findById(1L).orElseThrow(PlayerNotFoundException::new))
                .thenReturn(new Player());

        this.transactionService.debitValueOfPlayer(1L, this.transactionDTODebit);
    }

    /**
     * Method to test if a Debit Transaction was created but will fail
     * because the Player dont have enough found to do the transaction
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = EnoughFundsException.class)
    public void createDebitTransactionButFailBecausePlayerDontHaveEnoughFounds()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        Mockito.when(this.playerService.findPlayerById(1L))
                .thenReturn(PlayerDTO.fromEntity(this.player));

        this.transactionService.debitValueOfPlayer(1L, this.transactionDTODebit);

    }

    /**
     * Method to test if a Debit Transaction was created but will fail because the UUID of transaction already exists
     *
     * @throws InvalidTransactionTypeException   if the Type from Transaction was different from the acceptedType
     * @throws PlayerNotFoundException           If a Player was not found with the ID sent in parameter
     * @throws InvalidArgumentsException         If the ID sent in Parameter was different of the id of Player sent in transactions DTO
     * @throws EnoughFundsException              If the Value sent to the transaction was bigger then the balance of the Player
     * @throws NotUniqueTransactionUUIDException If the UUID already have one Transaction Registered
     */
    @Test(expected = NotUniqueTransactionUUIDException.class)
    public void createDebitTransactionButFailBecauseUUIDofTransactionAlreadyExists()
            throws InvalidArgumentsException, NotUniqueTransactionUUIDException,
            InvalidTransactionTypeException, PlayerNotFoundException, EnoughFundsException {

        this.player.setBalance(new BigDecimal("60.00"));

        Mockito.when(this.playerService.findPlayerById(1L))
                .thenReturn(PlayerDTO.fromEntity(this.player));

        Mockito.when(this.transactionRepository.findByTransaction(this.transactionDTODebit.getTransaction()))
                .thenReturn(Optional.of(TransactionDTO.toEntity(this.transactionDTOCredit)));

        this.transactionService.debitValueOfPlayer(1L, this.transactionDTODebit);
    }
}
