package com.recipe.services;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.recipe.models.UnitOfMeasure;
import com.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }


    public List<UnitOfMeasureCommand> listAll() {
        List<UnitOfMeasureCommand> list = new ArrayList<>();

        Iterable<UnitOfMeasure> iterable = unitOfMeasureRepository.findAll();
        iterable.forEach(unitOfMeasure -> {
            UnitOfMeasureCommand command = unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);
            log.info(String.valueOf(command));
            list.add(command);});
        return list;
    }
}
