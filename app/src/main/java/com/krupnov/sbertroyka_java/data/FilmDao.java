package com.krupnov.sbertroyka_java.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.krupnov.sbertroyka_java.pojo.Film;

import java.util.List;

@Dao
public interface FilmDao {
    @Query("SELECT * FROM films ORDER BY episodeId")
    LiveData<List<Film>> getAllFilms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilms(List<Film> films);

    @Query("DELETE FROM films")
    void deleteAllFilms();

}
