package com.recipe.services;

import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ImageService {
    private final RecipeRepository recipeRepository;

    public ImageService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public void saveImageFile(Long rid, MultipartFile file) throws IOException {
        Recipe recipe = recipeRepository.findById(rid).orElse(null);
        if(recipe == null)
            return;
        byte[] fileBytes = file.getBytes();
        Byte[] bytes = new Byte[fileBytes.length];
        Arrays.setAll(bytes, i -> fileBytes[i]);
        recipe.setImage(bytes);
        recipeRepository.save(recipe);
    }
}
