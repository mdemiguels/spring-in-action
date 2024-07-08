package tacos.utils;

import tacos.model.Ingredient;
import tacos.model.IngredientUDT;
import tacos.model.Taco;
import tacos.model.TacoUDT;

public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

}
