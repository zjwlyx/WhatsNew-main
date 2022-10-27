package com.laioffer.tinnews.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laioffer.tinnews.TinNewsApplication;
import com.laioffer.tinnews.database.TinNewsDatabase;
import com.laioffer.tinnews.model.News;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private final NewsApi newsApi;
    private  final TinNewsDatabase database;
    private String key = "a5b0b4f3e4bb2170b0b10aba8c1f9a0b";

    public NewsRepository(Context context) {
        newsApi = RetrofitClient.newInstance().create(NewsApi.class);
        database = ((TinNewsApplication)context.getApplicationContext()).getDatabase();
    }

    public LiveData<NewsResponse> getTopHeadlines(String country) {
        MutableLiveData<NewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
        newsApi.getTopHeadlines(country, key)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()) {
                            topHeadlinesLiveData.setValue(response.body());
                        } else {
                            topHeadlinesLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        topHeadlinesLiveData.setValue(null);
                        Log.d("GetTopHeadlines: ", t.toString());
                    }
                });
        return topHeadlinesLiveData;
    }

    public LiveData<NewsResponse> searchNews(String query) {
        MutableLiveData<NewsResponse> everyThingLiveData = new MutableLiveData<>();
        newsApi.getEverything(query, key)
                .enqueue(
                        new Callback<NewsResponse>() {
                            @Override
                            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                                if (response.isSuccessful()) {
                                    everyThingLiveData.setValue(response.body());
                                } else {
                                    everyThingLiveData.setValue(null);
                                }
                            }

                            @Override
                            public void onFailure(Call<NewsResponse> call, Throwable t) {
                                everyThingLiveData.setValue(null);
                            }
                        });
        return everyThingLiveData;
    }

    public LiveData<Boolean> favoriteNews(News news){
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        new FavoriteAsyncTask(database, resultLiveData).execute(news);
        return resultLiveData;
    }

    private static class FavoriteAsyncTask extends AsyncTask<News, Void, Boolean>{
        private final TinNewsDatabase database;
        private final MutableLiveData<Boolean> liveData;

        private FavoriteAsyncTask (TinNewsDatabase database, MutableLiveData<Boolean> liveData){
            this.database = database;
            this.liveData = liveData;
        }
        @Override
        protected Boolean doInBackground(News... news) {
            News thisNews = news[0];
            try{
                database.newsDao().saveNews(thisNews);
            } catch (Exception e){
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean success){
            liveData.setValue(success);
        }
    }

    public LiveData<List<News>> getAllSavedArticles(){
        return database.newsDao().getAllNews();
    }

    public void deleteSavedArticle(News news){
        AsyncTask.execute( () -> database.newsDao().deleteNews(news));
    }
}
