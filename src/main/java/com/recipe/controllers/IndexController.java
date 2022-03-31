package com.recipe.controllers;

import com.recipe.models.Category;
import com.recipe.models.UnitOfMeasure;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @RequestMapping("/")
    public String indexPage(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspon");
        System.out.println(categoryOptional);
        System.out.println(unitOfMeasure);

        return "index";
    }

}
