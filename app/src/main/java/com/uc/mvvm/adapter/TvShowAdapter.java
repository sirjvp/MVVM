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
import com.uc.mvvm.model.TvShow;
import com.uc.mvvm.ui.main.movie.MovieFragmentDirections;
import com.uc.mvvm.ui.main.tvShow.TvShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder>{

    private Context context;
    private List<TvShow> listTvShow;
    private List<TvShow> getListTvShow() {
        return listTvShow;
    }
    public void setListTvShow(List<TvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
    public TvShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_adapter, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final TvShowAdapter.CardViewViewHolder holder, int position) {
        final TvShow tvShow = getListTvShow().get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.cover);
        holder.lbl_title.setText(tvShow.getTitle());
        holder.lbl_popularity.setText(tvShow.getPopularity());
        holder.lbl_vote.setText(tvShow.getVote_average());
        holder.cardview.setOnClickListener(v -> {
            NavDirections action = TvShowFragmentDirections.actionTVtoDetail(null, tvShow);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
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