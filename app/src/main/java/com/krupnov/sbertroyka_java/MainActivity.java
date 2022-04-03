package com.krupnov.sbertroyka_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.krupnov.sbertroyka_java.adapters.FilmAdapter;
import com.krupnov.sbertroyka_java.pojo.Film;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFilms;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewFilms = findViewById(R.id.recyclerViewFilms);
        filmAdapter = new FilmAdapter();
        recyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilms.setAdapter(filmAdapter);
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        Film film2 = new Film();
        film1.setTitle("Новая Надежда");
        film2.setTitle("Империя наносит ответный удар");
        film1.setDirector("Лукас");
        film2.setDirector("Абрамс");
        film1.setProducer("Шиншила");
        film2.setProducer("Мышь");
        film1.setReleaseDate("12345");
        film2.setReleaseDate("67890");
        films.add(film1);
        films.add(film2);
        filmAdapter.setFilms(films);
    }
}