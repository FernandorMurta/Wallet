package fernando.murta.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@SpringBootApplication
public class WalletApplication {

	/**
	 * Method to initialization of the application
	 *
	 * @param args Arguments to init the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

}
