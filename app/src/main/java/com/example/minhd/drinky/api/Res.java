package com.example.minhd.drinky.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Res {
    @GET("place/autocomplete/json")
    Observable<SearchAutocompletePlaceReponse> searchPlace(
            @Query("key") String key,
            @Query("input") String input,
            @Query("language") String language
    );
}
