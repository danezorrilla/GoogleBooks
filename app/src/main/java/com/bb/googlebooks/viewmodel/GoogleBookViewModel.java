package com.bb.googlebooks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bb.googlebooks.model.GoogleBooks;
import com.bb.googlebooks.network.GoogleBookListRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GoogleBookViewModel extends AndroidViewModel {
    private GoogleBookListRetrofit googleBookListRetrofit;

    public GoogleBookViewModel(@NonNull Application application) {
        super(application);
        googleBookListRetrofit = new GoogleBookListRetrofit();
    }

    public Observable<GoogleBooks> getGoogleBookListRx(String bookTitle){
        return googleBookListRetrofit
                .getGoogleBookList(bookTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
