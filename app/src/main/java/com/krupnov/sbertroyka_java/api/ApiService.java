package com.krupnov.sbertroyka_java.api;

import com.krupnov.sbertroyka_java.pojo.FilmResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("films/?format=json")
    Observable<FilmResponse> getFilms();
}
