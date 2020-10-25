package com.uc.mvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.mvvm.R;
import com.uc.mvvm.model.Movie;
import com.uc.mvvm.ui.main.movie.MovieFragmentDirections;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>{

    private Context context;
    private List<Movie> listMovie;
    private List<Movie> getListMovie() {
        return listMovie;
    }
    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }
    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_adapter, parent, false);
        return new MovieAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.CardViewViewHolder holder, int position) {
        final Movie movie = getListMovie().get(position);
        Glide.with(context).load(movie.getCover()).centerCrop().into(holder.cover);
        holder.lbl_title.setText(movie.getTitle());
        holder.lbl_popularity.setText(movie.getPopularity());
        holder.lbl_vote.setText(movie.getVote_average());
        holder.cardview.setOnClickListener(v -> {
            NavDirections action = MovieFragmentDirections.actionMovieToDetail(movie, null);
            Navigation.findNavController(v).navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView lbl_title, lbl_popularity, lbl_vote;
        ImageView cover;
        CardView cardview;

        CardViewViewHolder(View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_popularity = itemView.findViewById(R.id.lbl_popularity);
            lbl_vote = itemView.findViewById(R.id.lbl_vote);
            cover = itemView.findViewById(R.id.cover_movie);
            cardview = itemView.findViewById(R.id.cardView);

        }
    }
}