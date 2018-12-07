package com.notes.crud.zenolab.crud.room.room_database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.notes.crud.zenolab.crud.room.Constants;
import com.notes.crud.zenolab.crud.room.DateRoomConverter;
import com.notes.crud.zenolab.crud.room.dao.NoteDao;
import com.notes.crud.zenolab.crud.room.entity.Note;

@Database(entities = {Note.class},version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();
    private static NoteDatabase noteDB;


    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }


    private static NoteDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context,NoteDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp() {noteDB = null;}

}
