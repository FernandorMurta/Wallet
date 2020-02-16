package fernando.murta.wallet.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Fernando Murta
 * @version 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public abstract class AbstractDTO implements Serializable {

	private Long id;

	private Date createdAt;

	private Date updatedAt;
}
