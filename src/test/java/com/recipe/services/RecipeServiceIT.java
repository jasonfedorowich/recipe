package com.recipe.services;

import com.recipe.commands.RecipeCommand;
import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeRepository recipeRepository;



    @Before
    public void setUp() throws Exception {

    }

    @Transactional
    @Test
    public void saveRecipeCommand() {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        command.setSource("Hello");

        RecipeCommand newCommand = recipeService.saveRecipeCommand(command);
        Assertions.assertNotEquals(newCommand, command);
        Assertions.assertEquals(newCommand.getSource(), "Hello");

    }
}