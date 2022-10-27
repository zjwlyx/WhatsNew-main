package com.laioffer.tinnews.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.laioffer.tinnews.model.NewsResponse;

public interface NewsApi {
    @GET("topnews/index")
    Call<NewsResponse> getTopHeadlines(@Query("word") String word, @Query("key") String key);

    @GET("topnews/index")
    Call<NewsResponse> getEverything(
            @Query("word") String word, @Query("key") String key);
}
