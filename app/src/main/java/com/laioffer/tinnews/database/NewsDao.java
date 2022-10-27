package com.laioffer.tinnews.database;

import androidx.annotation.BinderThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.laioffer.tinnews.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void saveNews(News news);

    @Query("SELECT * FROM News")
    LiveData<List<News>> getAllNews();

    @Delete
    void deleteNews(News news);
}
