package tacos.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.model.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;
	private final TacoRepository tacoRepository;

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
    }

    @ModelAttribute
	public void addIngredients(Model model) {
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

		Type[] types = Ingredient.Type.values();

		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignFrom() {
		return "design";
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors,
							  @ModelAttribute TacoOrder tacoOrder) {

		if(errors.hasErrors()) {
			return "design";
		}

		tacoOrder.addTaco(taco);
		tacoRepository.save(taco);
		log.info("Processing taco: {}", taco);

		return "redirect:/orders/current";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		 return ingredients
				 .stream()
				 .filter(x -> x.getType().equals(type))
				 .collect(Collectors.toList());
	}

}
