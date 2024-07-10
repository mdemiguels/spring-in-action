package tacos.model;

import java.io.Serializable;
import java.util.*;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import tacos.utils.TacoUDRUtils;

@Data
@Table("orders")
public class TacoOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private UUID id = Uuids.timeBased();

	@Column("placed_at")
	private Date placedAt = new Date();

	@Column("delivery_name")
	@NotBlank(message = "Delivery name is required")
	private String deliveryName;

	@Column("delivery_street")
	@NotBlank(message = "Street is required")
	private String deliveryStreet;

	@Column("delivery_city")
	@NotBlank(message = "City is required")
	private String deliveryCity;

	@Column("delivery_state")
	@NotBlank(message = "State is required")
	private String deliveryState;

	@Column("delivery_zip")
	@NotBlank(message = "Zip code is required")
	private String deliveryZip;

	@Column("cc_number")
	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;

	@Column("cc_expiration")
	@Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Column("cc_cvv")
	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String ccCVV;

	@Column("tacos")
	private List<TacoUDT> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(TacoUDRUtils.toTacoUDT(taco));
	}
	
}