package com.recipe.models;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String notes;

    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Note)) return false;
        final Note other = (Note) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$recipe = this.getRecipe();
        final Object other$recipe = other.getRecipe();
        if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
        final Object this$notes = this.getNotes();
        final Object other$notes = other.getNotes();
        if (this$notes == null ? other$notes != null : !this$notes.equals(other$notes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Note;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $recipe = this.getRecipe();
        result = result * PRIME + ($recipe == null ? 43 : $recipe.hashCode());
        final Object $notes = this.getNotes();
        result = result * PRIME + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    public String toString() {
        return "Note(id=" + this.getId() + ", recipe=" + this.getRecipe() + ", notes=" + this.getNotes() + ")";
    }
}
