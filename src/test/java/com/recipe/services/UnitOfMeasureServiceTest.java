package com.recipe.services;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.recipe.models.UnitOfMeasure;
import com.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class UnitOfMeasureServiceTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAll() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(100L);
        unitOfMeasure.setDescription("hello");

        unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository, new UnitOfMeasureToUnitOfMeasureCommand());
        Set<UnitOfMeasure> set = new HashSet<>();
        set.add(unitOfMeasure);

        when(unitOfMeasureRepository.findAll()).thenReturn(set);
        List<UnitOfMeasureCommand> list = unitOfMeasureService.listAll();
        Long l = 100L;
        assertEquals(l, list.get(0).getId());

    }
}