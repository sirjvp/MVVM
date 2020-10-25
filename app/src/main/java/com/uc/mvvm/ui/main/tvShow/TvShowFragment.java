package com.uc.mvvm.ui.main.tvShow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.mvvm.R;
import com.uc.mvvm.adapter.TvShowAdapter;
import com.uc.mvvm.model.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {

    @BindView(R.id.rv_tvshow)
    RecyclerView rv_tvshow;

    private TvShowViewModel viewModel;
    private TvShowAdapter adapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        rv_tvshow.setLayoutManager((new LinearLayoutManager(getActivity())));
        adapter = new TvShowAdapter(getContext());

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);

        TvShow tvShow = new TvShow();
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
        if(tvShows != null){
            // set adapter
            adapter.setListTvShow(tvShows);
            adapter.notifyDataSetChanged();
            rv_tvshow.setAdapter(adapter);
            // add adapter ro recyclerview
        }
    };
}