package com.recipe.services;

import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(recipeRepository);
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
}