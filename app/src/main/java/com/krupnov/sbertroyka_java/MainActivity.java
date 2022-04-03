package com.krupnov.sbertroyka_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.krupnov.sbertroyka_java.adapters.FilmAdapter;
import com.krupnov.sbertroyka_java.data.AppFilmDatabase;
import com.krupnov.sbertroyka_java.pojo.Film;
import com.krupnov.sbertroyka_java.screens.films.FilmViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFilms;
    private FilmAdapter filmAdapter;
    private FilmViewModel viewModel;
    private AppFilmDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewFilms = findViewById(R.id.recyclerViewFilms);
        filmAdapter = new FilmAdapter();
        filmAdapter.setFilms(new ArrayList<Film>());
        recyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilms.setAdapter(filmAdapter);
        viewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        viewModel.getFilms().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                Toast.makeText(MainActivity.this, "ALARM", Toast.LENGTH_SHORT).show();
                filmAdapter.setFilms(films);
            }
        });
//        viewModel.getErrors().observe(this, new Observer<Throwable>() {
//            @Override
//            public void onChanged(Throwable throwable) {
//                if (throwable != null) {
//                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    viewModel.clearErrors();
//                }
////            }
//        });
        viewModel.loadData();
    }


}