package com.bb.googlebooks.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {FavoriteBooksEntity.class})
public abstract class FavoriteBooksDB extends RoomDatabase {
    public abstract FavoriteBooksDAO getFavoriteBooksDAO();
}
