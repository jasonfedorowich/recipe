package com.recipe.controllers;

import com.recipe.commands.RecipeCommand;
import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



public class IndexControllerTest {

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

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

    @Test
    public void testShowPage() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(10L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipes/10")).andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testPostNewRecipe() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(100L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "something"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/100"));


    }

    @Test
    public void testDeleteRecipe() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipes/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes"));

    }


}