package com.bb.googlebooks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bb.googlebooks.R;
import com.bb.googlebooks.database.FavoriteBooksDB;
import com.bb.googlebooks.database.FavoriteBooksEntity;
import com.bb.googlebooks.model.GoogleBooks;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bb.googlebooks.util.DebugLogger.logDebug;

public class GoogleBookAdapter extends RecyclerView.Adapter<GoogleBookAdapter.GoogleBookViewHolder> {

    private GoogleBooks googleBooks;
    private FavoriteBooksDB favoriteBooksDB;

    public GoogleBookAdapter(GoogleBooks googleBooks) {
        this.googleBooks = googleBooks;
    }

    @NonNull
    @Override
    public GoogleBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        favoriteBooksDB = Room.databaseBuilder(
                parent.getContext(),
                FavoriteBooksDB.class,
                "favoritebooks.db")
                .allowMainThreadQueries().build();

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.google_books_layout, parent, false);
        return new GoogleBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoogleBookViewHolder holder, int position) {
//        Glide.with(holder.itemView.getContext())
//                .load(googleBooks.getItems().get(position).getVolumeInfo().getImageLinks().getThumbnail())
//                .into(holder.googleBooksThumbnail);

        holder.googleBooksTitle.setText(googleBooks.getItems().get(position).getVolumeInfo().getTitle());
        holder.googleBooksAuthor.setText(googleBooks.getItems().get(position).getVolumeInfo().getAuthors().toString());
        holder.googleBooksDesc.setText(googleBooks.getItems().get(position).getVolumeInfo().getDescription());

        holder.googleBookFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    logDebug("Favorite Selected");
                    favoriteBooksDB.getFavoriteBooksDAO().addFavoriteBook(new FavoriteBooksEntity(
                            googleBooks.getItems().get(position).getVolumeInfo().getTitle()
                    ));
                    logDebug("Book is added to Favorite");
                    Toast.makeText(holder.itemView.getContext(), "Book is added to Favorite", Toast.LENGTH_SHORT).show();
                } else{
                    logDebug("Favorite UnSelected");
                    favoriteBooksDB.getFavoriteBooksDAO().deleteFavoriteBook(new FavoriteBooksEntity(
                            googleBooks.getItems().get(position).getVolumeInfo().getTitle()
                    ));
                    logDebug("Book is removed from Favorite");
                    Toast.makeText(holder.itemView.getContext(), "Book is removed from Favorite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return googleBooks.getItems().size();
    }

    public class GoogleBookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.google_bookList_thumbnail)
        ImageView googleBooksThumbnail;

        @BindView(R.id.google_bookList_title)
        TextView googleBooksTitle;

        @BindView(R.id.google_bookList_author)
        TextView googleBooksAuthor;

        @BindView(R.id.google_bookList_desc)
        TextView googleBooksDesc;

        @BindView(R.id.google_bookList_favorite)
        ToggleButton googleBookFavorite;

        public GoogleBookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
