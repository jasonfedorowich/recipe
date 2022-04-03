package com.recipe.converters;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp(){
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(1L);
        command.setDescription("hello");
        Long lo = 1L;
        UnitOfMeasure unitOfMeasure = converter.convert(command);
        assertNotNull(unitOfMeasure);
        assertEquals(lo, unitOfMeasure.getId());


    }
}