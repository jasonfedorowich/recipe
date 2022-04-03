package com.recipe.converters;

import com.recipe.commands.NotesCommand;
import com.recipe.models.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class NotesToNotesCommand implements Converter<Note, NotesCommand>{

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Note source) {
        if(source == null)
            return null;


        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getNotes());
        return notesCommand;
    }
}
