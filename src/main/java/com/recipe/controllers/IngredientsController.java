package com.recipe.controllers;

import com.recipe.commands.IngredientCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.services.IngredientService;
import com.recipe.services.RecipeService;
import com.recipe.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class IngredientsController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientsController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/recipes/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/index";
    }

    @RequestMapping("/recipes/{id}/ingredients/{ingId}")
    public String findIngredient(@PathVariable String rid, @PathVariable String iid, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(rid), Long.valueOf(iid)));
        return "recipe/ingredient/show";
    }

    @RequestMapping("/recipes/{rid}/ingredient/{iid}/update")
    public String updateIngredient(@PathVariable String rid, @PathVariable String iid, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(rid), Long.valueOf(iid)));
        model.addAttribute("uomList", unitOfMeasureService.listAll());
        return "recipe/ingredient/form";
    }

    @PostMapping("/recipes/{rid}/ingredient")
    public String save(@ModelAttribute IngredientCommand command){
        IngredientCommand command1 = ingredientService.saveIngredientCommand(command);
        return "redirect:/recipe/"+ command1.getRecipeId() + "/ingredient/" + command1.getId() + "/show";
    }

    @RequestMapping("/recipes/{rid}/ingredient/new")
    public String newIngredient(@PathVariable String rid, Model model){
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(rid));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(command.getId());
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure( new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAll());
        return "recipe/ingredient/form";
    }

    @RequestMapping("/recipes/{rid}/ingredient/{iid}/delete")
    public String deleteIngredient(@PathVariable String rid, @PathVariable String iid, Model model){
        recipeService.deleteFromRecipeByIngId(Long.valueOf(rid), Long.valueOf(iid));
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(rid)));
        return "redirect:/recipe/ingredient/index";
    }

}
