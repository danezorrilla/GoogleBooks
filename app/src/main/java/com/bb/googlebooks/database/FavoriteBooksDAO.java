package com.bb.googlebooks.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface FavoriteBooksDAO {

    @Insert
    void addFavoriteBook(FavoriteBooksEntity favoriteBooksEntity);

    @Delete
    void deleteFavoriteBook(FavoriteBooksEntity favoriteBooksEntity);
}
