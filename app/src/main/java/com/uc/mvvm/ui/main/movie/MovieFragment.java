package com.uc.mvvm.ui.main.movie;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uc.mvvm.R;
import com.uc.mvvm.adapter.MovieAdapter;
import com.uc.mvvm.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.rv_movie)
    RecyclerView rv_movies;

    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        rv_movies.setLayoutManager((new LinearLayoutManager(getActivity())));
        adapter = new MovieAdapter(getContext());

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

        Movie movie = new Movie();
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if(movies != null){
            // set adapter
            adapter.setListMovie(movies);
            adapter.notifyDataSetChanged();
            rv_movies.setAdapter(adapter);
            // add adapter ro recyclerview
        }
    };
}