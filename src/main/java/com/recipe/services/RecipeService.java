package com.recipe.services;

import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes(){
        log.debug("I'm in the service");
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().forEach(list::add);
        return list;
    }
}
