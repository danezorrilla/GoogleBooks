package com.bb.googlebooks.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavoriteBooks")
public class FavoriteBooksEntity {

    @PrimaryKey(autoGenerate = true)
    private int favoriteBookID;

    @ColumnInfo(name = "favoriteBookTitle")
    private String favoriteBookTitle;

    public FavoriteBooksEntity(int favoriteBookID, String favoriteBookTitle) {
        this.favoriteBookID = favoriteBookID;
        this.favoriteBookTitle = favoriteBookTitle;
    }

    @Ignore
    public FavoriteBooksEntity(String favoriteBookTitle) {
        this.favoriteBookTitle = favoriteBookTitle;
    }

    public int getFavoriteBookID() {
        return favoriteBookID;
    }

    public void setFavoriteBookID(int favoriteBookID) {
        this.favoriteBookID = favoriteBookID;
    }

    public String getFavoriteBookTitle() {
        return favoriteBookTitle;
    }

    public void setFavoriteBookTitle(String favoriteBookTitle) {
        this.favoriteBookTitle = favoriteBookTitle;
    }
}
