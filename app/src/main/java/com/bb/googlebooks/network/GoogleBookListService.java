package com.bb.googlebooks.network;

import com.bb.googlebooks.model.GoogleBooks;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBookListService {

    @GET("/books/v1/volumes")
    public Observable<GoogleBooks> getGoogleBookList(@Query("q") String query, @Query("key") String key);
}
