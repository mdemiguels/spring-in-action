package tacos.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    public DataLoader(TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
        Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
        Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
        Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
        Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
        Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
        Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

        ingredientRepository.save(flourTortilla);
        ingredientRepository.save(cornTortilla);
        ingredientRepository.save(groundBeef);
        ingredientRepository.save(carnitas);
        ingredientRepository.save(tomatoes);
        ingredientRepository.save(lettuce);
        ingredientRepository.save(cheddar);
        ingredientRepository.save(jack);
        ingredientRepository.save(salsa);
        ingredientRepository.save(sourCream);

        Taco taco1 = new Taco();
        taco1.setName("Carnitas");
        taco1.setCreatedAt(new Date());
        taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, tomatoes, lettuce, cheddar, salsa));

        Taco taco2 = new Taco();
        taco2.setName("Veggie");
        taco2.setCreatedAt(new Date());
        taco2.setIngredients(Arrays.asList(cornTortilla, tomatoes, lettuce, cheddar, salsa));

        tacoRepository.save(taco1);
        tacoRepository.save(taco2);

    }
}
