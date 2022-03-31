package com.recipe.bootstrap;

import com.recipe.models.*;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {


    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDifficulty(Difficulty.EASY);


        Category category = new Category();
        category.setDescription("category1");

        categoryRepository.save(category);

        recipe.getCategories().add(category);
        recipe.setDirections("Mix it up");
        recipe.setPrepTime(1);
        recipe.setServings(1);
        recipe.setUrl("my.com");
        recipe.setSource("my-source");
        Note note = new Note();
        note.setNotes("note1");
        note.setRecipe(recipe);
        recipe.setNotes(note);
        recipe.setDescription("mydescript1");


        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Units");

        Ingredient ingredient1 = new Ingredient("Chicken", BigDecimal.valueOf(4), unitOfMeasure, recipe);
        Ingredient ingredient2 = new Ingredient("Chicken soup", BigDecimal.valueOf(5), unitOfMeasure, recipe);

        unitOfMeasureRepository.save(unitOfMeasure);
        ingredient1.setUnitOfMeasure(unitOfMeasure);
        ingredient2.setUnitOfMeasure(unitOfMeasure);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        recipeRepository.save(recipe);

        log.info("Finished------");





    }
}
