package com.recipe.controllers;

import com.recipe.models.Recipe;
import com.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void indexPage() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<List<Recipe>> arguementCaptor = ArgumentCaptor.forClass(List.class);
        String result = recipeController.getAllRecipes(model);
        Assertions.assertEquals("recipe/index", result);
        verify(model, times(1)).addAttribute(eq("recipes"), arguementCaptor.capture());
        //Set<Recipe> set = argumentCaptor.capture();
        //arguementCaptor.capture();

        List<Recipe> setInController = arguementCaptor.getValue();
        List<List<Recipe>> te = arguementCaptor.getAllValues();
        Assertions.assertEquals(2, setInController.size());
    }
}