package com.bb.googlebooks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bb.googlebooks.R;
import com.bb.googlebooks.adapter.GoogleBookAdapter;
import com.bb.googlebooks.model.GoogleBooks;
import com.bb.googlebooks.util.DebugLogger;
import com.bb.googlebooks.viewmodel.GoogleBookViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private GoogleBookViewModel viewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.google_books_title)
    TextView googleBookTitle;

    @BindView(R.id.google_books_list)
    RecyclerView googleBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(GoogleBookViewModel.class);

//        compositeDisposable.add(viewModel.getGoogleBookListRx("Harry Potter")
//                .subscribe(googleBooks -> {displayGoogleBookListRx(googleBooks);}, throwable -> {
//                    DebugLogger.logError(throwable);
//                }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void displayGoogleBookListRx(GoogleBooks googleBooks){
        for(int i = 0; i < googleBooks.getItems().size(); i++){
            DebugLogger.logDebug("RxJava : " + googleBooks.getItems().get(i).getVolumeInfo().getTitle());
            googleBookList.setLayoutManager(new LinearLayoutManager(this));
            googleBookList.setAdapter(new GoogleBookAdapter(googleBooks));
        }
    }

    @OnClick(R.id.google_book_search)
    public void searchMovieTitle(View view){
        System.out.println("Button is clicked");
        String bookTitle = googleBookTitle.getText().toString();
        System.out.println(bookTitle);

        compositeDisposable.add(viewModel.getGoogleBookListRx(bookTitle)
                .subscribe(googleBooks -> {displayGoogleBookListRx(googleBooks);}, throwable -> {
                    DebugLogger.logError(throwable);
                }));
    }

}
