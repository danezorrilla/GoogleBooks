package com.bb.googlebooks.network;

import com.bb.googlebooks.model.GoogleBooks;
import com.bb.googlebooks.util.Constants;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleBookListRetrofit {

    private GoogleBookListService googleBookListService;

    private OkHttpClient client;

    public GoogleBookListRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        googleBookListService = createService(getRetrofit());

    }

    private Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private GoogleBookListService createService(Retrofit retrofit){
        return retrofit.create(GoogleBookListService.class);
    }

    public Observable<GoogleBooks> getGoogleBookList(String bookTitle){
        return googleBookListService.getGoogleBookList(bookTitle, Constants.API_KEY);
    }
}
