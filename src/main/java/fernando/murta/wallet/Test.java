package fernando.murta.wallet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = Test._PATH)
public class Test {

	final static String _PATH = "/test";


	/**
	 * Method to test Spring security
	 *
	 * @return Test Message
	 */
	@GetMapping()
	public ResponseEntity<String> test() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Hello World");
	}


	/**
	 * Method to test Spring security
	 *
	 * @return Test Message
	 */
	@PutMapping()
	public ResponseEntity<String> testFuck() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Hello World2 ");
	}

	/**
	 * Method to test Spring security
	 *
	 * @return Test Message
	 */
	@PutMapping(value = "/fuck")
	public ResponseEntity<String> testFuck2() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Hello World3 ");
	}
}
