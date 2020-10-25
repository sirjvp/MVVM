package com.uc.mvvm.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.mvvm.R;
import com.uc.mvvm.model.Genre;
import com.uc.mvvm.model.Movie;
import com.uc.mvvm.model.TvShow;
import com.uc.mvvm.ui.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.cover_image)
    ImageView cover;

    @BindView(R.id.name_text)
    TextView title;

//    @BindView(R.id.)
//    TextView year;

    @BindView(R.id.genre_text)
    TextView genre;

    @BindView(R.id.adult_text)
    TextView adult;

    @BindView(R.id.description_text)
    TextView description;

//    @BindView(R.id.popularity_text)
//    TextView popularity;

    @BindView(R.id.date_text)
    TextView date;

//    @BindView(R.id.detail_cast)
//    TextView cast;

    @BindView(R.id.poster_image)
    ImageView poster;

//    @BindView(R.id.detail_vote)
//    TextView vote;

    @BindView(R.id.rv_cast)
    RecyclerView rv_cast;

    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel viewModel;
    private DetailCastAdapter adapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);
        rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailCastAdapter(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvshow();

            if (movie != null) {
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeTvShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }

        }
//        button.setOnClickListener(v -> {
//            NavDirections action = DetailFragmentDirections.actionMovieFragment();
//            Navigation.findNavController(view).navigate(action);
//        });
    }

    private void observeTvShowViewModel(int idShow) {
        viewModel.getTvShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + "|");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });

        viewModel.getShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }

    private void observeMovieViewModel(int idMovie) {
        viewModel.getMovieGenre(idMovie).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        genre.append(g.getNama() + " | ");
                    } else {
                        genre.append(g.getNama());
                    }
                }
            }
        });
        viewModel.getMovieCast(idMovie).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }

    private void initShow(TvShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(tvShow.getCover()).centerCrop().into(cover);
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(poster);
        adult.setVisibility(View.INVISIBLE);
        title.setText(tvShow.getTitle());
//        vote.setText(tvShow.getVote_average());
        description.setText(tvShow.getDescription());
//        popularity.setText(tvShow.getPopularity());
        date.setText(tvShow.getReleaseDate());
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getCover()).into(cover);
        Glide.with(getActivity()).load(movie.getPoster()).into(poster);
        adult.setVisibility(View.VISIBLE);
        if (movie.getAdult().equalsIgnoreCase("false")) {
            adult.setText("All age");
        } else {
            adult.setText("Adult");
        }
        title.setText(movie.getTitle());
//        vote.setText(movie.getVote_average());
        description.setText(movie.getDescription());
//        popularity.setText(movie.getPopularity());
        date.setText(movie.getReleaseDate());
    }

}