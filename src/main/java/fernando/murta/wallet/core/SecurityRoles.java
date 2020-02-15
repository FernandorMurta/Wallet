package fernando.murta.wallet.core;

import lombok.Getter;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Getter
public enum SecurityRoles {

	ADMIN(0L, "ADMIN"),
	USER(1L, "USER");

	/**
	 * Constructor
	 *
	 * @param id   ID from Enum
	 * @param role Role Description
	 */
	SecurityRoles(Long id, String role) {
		this.id = id;
		this.role = role;
	}

	private Long id;

	private String role;
}
