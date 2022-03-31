package com.recipe.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void init(){
        category = new Category();
    }

    @Test
    public void getId() {
        category.setId(4L);
        Assertions.assertEquals(category.getId(), 4L);
    }

    @Test
    public void getDescription() {

        category.setDescription("Hello");
        Assertions.assertEquals(category.getDescription(), "Hello");
    }

    @Test
    public void getRecipes() {
    }
}