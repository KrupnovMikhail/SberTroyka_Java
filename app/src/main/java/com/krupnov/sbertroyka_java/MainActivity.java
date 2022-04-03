package com.krupnov.sbertroyka_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.krupnov.sbertroyka_java.adapters.FilmAdapter;
import com.krupnov.sbertroyka_java.api.ApiFactory;
import com.krupnov.sbertroyka_java.api.ApiService;
import com.krupnov.sbertroyka_java.pojo.Film;
import com.krupnov.sbertroyka_java.pojo.FilmResponse;

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

    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewFilms = findViewById(R.id.recyclerViewFilms);
        filmAdapter = new FilmAdapter();
        filmAdapter.setFilms(new ArrayList<>());
        recyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilms.setAdapter(filmAdapter);

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FilmResponse>() {
                    @Override
                    public void accept(FilmResponse filmResponse) throws Exception {
                        filmAdapter.setFilms(filmResponse.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }
}