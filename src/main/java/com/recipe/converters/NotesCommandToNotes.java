package com.recipe.converters;

import com.recipe.commands.NotesCommand;
import com.recipe.models.Note;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NotesCommand source) {
        if(source == null)
            return null;

        final Note notes = new Note();
        notes.setId(source.getId());
        notes.setNotes(source.getRecipeNotes());
        return notes;
    }
}
