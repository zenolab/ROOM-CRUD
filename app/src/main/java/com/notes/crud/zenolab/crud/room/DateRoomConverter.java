package com.notes.crud.zenolab.crud.room;

import androidx.room.TypeConverter;

import java.util.Date; // for fetch calendar date

public class DateRoomConverter {

    @TypeConverter
    public static Date toData(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
