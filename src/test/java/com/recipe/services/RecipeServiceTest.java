package com.recipe.services;

import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class RecipeServiceTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;



    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        List<Recipe> recipesArr = new ArrayList<>();
        recipesArr.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesArr);

        List<Recipe> recipes = recipeService.getAllRecipes();
        Assertions.assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getById(){
        Recipe recipe = new Recipe();
        recipe.setId(10L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        Recipe recipe1 = recipeService.findById(10L);
        Assertions.assertEquals(recipe1, recipe);


    }
}