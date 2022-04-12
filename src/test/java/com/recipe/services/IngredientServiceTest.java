package com.recipe.services;

import com.recipe.commands.IngredientCommand;
import com.recipe.converters.IngredientCommandToIngredient;
import com.recipe.converters.IngredientToIngredientCommand;
import com.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.recipe.models.Ingredient;
import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IngredientServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.openMocks(this);
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(recipeRepository, new UnitOfMeasureCommandToUnitOfMeasure());
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientService(recipeRepository, ingredientCommandToIngredient, ingredientToIngredientCommand, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1000L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(100L);
        recipe.getIngredients().add(ingredient);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Ingredient ingredient1 = ingredientService.findByRecipeIdAndIngredientId(1000L, 100L);
        assertEquals(ingredient1, ingredient);


    }

    @Test
    public void testSaveCommand(){
        Recipe recipe = new Recipe();
        recipe.setId(1000L);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setAmount(BigDecimal.ONE);
        ingredient.setRecipeId(recipe.getId());


        IngredientCommand command = ingredientService.saveIngredientCommand(ingredient);
        assertEquals(command.getAmount(), ingredient.getAmount());
    }
}