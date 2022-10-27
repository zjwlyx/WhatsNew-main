package com.laioffer.tinnews.ui.save;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.News;
import com.laioffer.tinnews.repository.NewsRepository;

import java.util.List;

public class SaveViewModel extends ViewModel {
    private final NewsRepository repository;

    public SaveViewModel(NewsRepository repository){
        this.repository = repository;
    }

    public LiveData<List<News>> getAllSavedArticles(){
        return repository.getAllSavedArticles();
    }

    public void deleteSavedArticle(News news){
        repository.deleteSavedArticle(news);
    }
}
