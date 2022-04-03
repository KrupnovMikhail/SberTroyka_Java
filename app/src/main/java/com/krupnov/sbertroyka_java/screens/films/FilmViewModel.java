package com.krupnov.sbertroyka_java.screens.films;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.krupnov.sbertroyka_java.MainActivity;
import com.krupnov.sbertroyka_java.api.ApiFactory;
import com.krupnov.sbertroyka_java.api.ApiService;
import com.krupnov.sbertroyka_java.data.AppFilmDatabase;
import com.krupnov.sbertroyka_java.pojo.Film;
import com.krupnov.sbertroyka_java.pojo.FilmResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FilmViewModel extends AndroidViewModel {

    private static AppFilmDatabase db;
    private LiveData<List<Film>> films;
    private CompositeDisposable compositeDisposable;

    public FilmViewModel(@NonNull Application application) {
        super(application);
        db = AppFilmDatabase.getInstance(application);
        films = db.filmDao().getAllFilms();
    }

    public LiveData<List<Film>> getFilms() {
        return films;
    }

    @SuppressWarnings("unchecked")
    private void insertFilms(List<Film> films) {
        new InsertFilmsTask().execute(films);
    }

    private static class InsertFilmsTask extends AsyncTask<List<Film>, Void, Void> {
        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Film>... lists) {
            if (lists != null && lists.length > 0) {
                db.filmDao().insertFilms(lists[0]);
            }
            return null;
        }
    }

    private void deleteAllFilms(){
        new DeleteAllFilmsTask().execute();
    }

    private static class DeleteAllFilmsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            db.filmDao().deleteAllFilms();
            return null;
        }
    }


    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FilmResponse>() {
                    @Override
                    public void accept(FilmResponse filmResponse) throws Exception {
                        deleteAllFilms();
                        insertFilms(filmResponse.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
