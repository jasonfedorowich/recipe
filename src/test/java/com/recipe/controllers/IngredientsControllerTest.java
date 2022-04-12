package com.recipe.controllers;

import com.recipe.commands.RecipeCommand;
import com.recipe.services.IngredientService;
import com.recipe.services.RecipeService;
import com.recipe.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class IngredientsControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientsController ingredientsController;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        ingredientsController = new IngredientsController(recipeService, ingredientService, unitOfMeasureService);


    }



    @Test
    public void listIngredients() throws Exception {
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(1000L);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientsController).build();
        mockMvc.perform(get("/recipes/1000/ingredients")).andExpect(status().isOk()).andExpect(view().name("recipe/ingredient/index"));

    }

    @Test
    public void testSave(){

    }
}