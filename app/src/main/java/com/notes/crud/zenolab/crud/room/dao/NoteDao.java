package com.notes.crud.zenolab.crud.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.notes.crud.zenolab.crud.room.Constants;
import com.notes.crud.zenolab.crud.room.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM "+ Constants.TABLE_NAME_NOTE)
    List<Note> getNotes();

    /**
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    long insertNote(Note note);

    /**
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void updateNote(Note note);

    /**
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void deleteNote(Note note);

    // Note... is varargs, here note is an array
    /**
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    void deleteNotes(Note... note);

}
