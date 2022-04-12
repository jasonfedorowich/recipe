package com.recipe.services;

import com.recipe.commands.IngredientCommand;
import com.recipe.converters.IngredientCommandToIngredient;
import com.recipe.converters.IngredientToIngredientCommand;
import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientService(RecipeRepository recipeRepository, IngredientCommandToIngredient ingredientCommandToIngredient, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    public Ingredient findByRecipeIdAndIngredientId(Long rid, Long iid) {
        Recipe recipe = recipeRepository.findById(rid).orElse(null);
        if(recipe == null)
            return null;

        for(Ingredient ingredient: recipe.getIngredients()){
            if(ingredient.getId().equals(iid))
                return ingredient;
        }
        return null;

    }

    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient ingredient = ingredientCommandToIngredient.convert(command);
        if(ingredient == null)
            return null;
        if(ingredient.getRecipe() == null)
            return ingredientToIngredientCommand.convert(ingredient);
        if(ingredient.getId() == null){
            ingredient.getRecipe().addIngredient(ingredient);
            recipeRepository.save(ingredient.getRecipe());
            return ingredientToIngredientCommand.convert(ingredient);
        }
        Ingredient in = null;
        for(Ingredient ingredient1: ingredient.getRecipe().getIngredients()){
            if(Objects.equals(ingredient1.getId(), ingredient.getId())){
                in = ingredient1;
                break;
            }
        }
        if(in != null){
            in.setDescription(ingredient.getDescription());
            in.setUnitOfMeasure(ingredient.getUnitOfMeasure());
            in.setAmount(ingredient.getAmount());

        }else{
            ingredient.getRecipe().addIngredient(ingredient);
        }

        recipeRepository.save(ingredient.getRecipe());
        return ingredientToIngredientCommand.convert(ingredient);

    }
}
