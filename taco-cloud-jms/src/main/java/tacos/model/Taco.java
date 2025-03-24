package tacos.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	private Date createdAt = new Date();

	@Size(min = 1, message = "You must choose at least 1 ingredient")
	@ManyToMany
	private List<Ingredient> ingredients;

	public void addIngredients(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
}