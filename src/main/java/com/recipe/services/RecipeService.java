package com.recipe.services;

import com.recipe.commands.RecipeCommand;
import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.exceptions.NotFoundException;
import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeService(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    public List<Recipe> getAllRecipes(){
        log.debug("I'm in the service");
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().forEach(list::add);
        return list;
    }

    public Recipe findById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new NotFoundException("Not Found");
        return recipeOptional.get();

    }

    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command){
        log.info("HI here");
        Recipe recipe = recipeCommandToRecipe.convert(command);
        assert recipe != null;
        recipeRepository.save(recipe);
        return recipeToRecipeCommand.convert(recipe);

    }

    @Transactional
    public RecipeCommand findCommandById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipeToRecipeCommand.convert(recipe);
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public void deleteFromRecipeByIngId(Long rid, Long iid) {
        Recipe recipe = recipeRepository.findById(rid).orElse(null);
        if(recipe == null)
            return;
        recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(iid));
        recipeRepository.save(recipe);
    }
}
