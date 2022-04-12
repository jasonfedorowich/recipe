package com.recipe.services;

import com.recipe.models.Recipe;
import com.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class ImageServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        imageService = new ImageService(recipeRepository);

    }


    @Test
    public void saveImageFile() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("file", "file1.txt", "text/plain", "hello world".getBytes());
        Recipe recipe =new Recipe();
        recipe.setId(199L);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        imageService.saveImageFile(199L, multipartFile);
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}