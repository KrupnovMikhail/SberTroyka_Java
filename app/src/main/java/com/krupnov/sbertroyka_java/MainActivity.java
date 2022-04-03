package com.krupnov.sbertroyka_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.krupnov.sbertroyka_java.adapters.FilmAdapter;
import com.krupnov.sbertroyka_java.api.ApiFactory;
import com.krupnov.sbertroyka_java.api.ApiService;
import com.krupnov.sbertroyka_java.pojo.Film;
import com.krupnov.sbertroyka_java.pojo.FilmResponse;
import com.krupnov.sbertroyka_java.screens.films.FilmViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFilms;
    private FilmAdapter filmAdapter;
    private FilmViewModel viewModel;



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
                filmAdapter.setFilms(films);
            }
        });

        viewModel.loadData();
    }


}