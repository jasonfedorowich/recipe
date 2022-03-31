package com.recipe.repositories;


import com.recipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
