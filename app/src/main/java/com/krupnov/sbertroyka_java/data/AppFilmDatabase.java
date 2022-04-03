package com.krupnov.sbertroyka_java.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.krupnov.sbertroyka_java.pojo.Film;

@Database(entities = Film.class, version = 1, exportSchema = false)
public abstract class AppFilmDatabase extends RoomDatabase {
    private static String DB_NAME = "films.db";
    private static AppFilmDatabase database;

    private static final Object LOCK = new Object();

    public static AppFilmDatabase getInstance(Context context) {
        synchronized (LOCK){
            if (database == null) {
                database = Room.databaseBuilder(context, AppFilmDatabase.class, DB_NAME).build();
            }
            return database;
        }
    }

    public abstract FilmDao filmDao();
}
