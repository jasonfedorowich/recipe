package com.recipe.controllers;

import com.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipes", "/recipes/index"})
    String getAllRecipes(Model model){
        model.addAttribute("recipes", this.recipeService.getAllRecipes());
        return "recipe/index";
    }
}