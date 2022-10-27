package com.laioffer.tinnews.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.laioffer.tinnews.model.News;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase  extends RoomDatabase {
    public abstract NewsDao newsDao();
}
