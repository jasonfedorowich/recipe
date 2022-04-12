package com.recipe.converters;

import com.recipe.commands.IngredientCommand;
import com.recipe.models.Ingredient;
import com.recipe.repositories.RecipeRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(RecipeRepository recipeRepository, UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.recipeRepository = recipeRepository;
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        if(source.getRecipeId() != null)
            ingredient.setRecipe(recipeRepository.findById(source.getRecipeId()).orElse(null));
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
