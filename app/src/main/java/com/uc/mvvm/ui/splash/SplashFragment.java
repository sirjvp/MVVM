package com.uc.mvvm.ui.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uc.mvvm.R;
import com.uc.mvvm.ui.MainActivity;

import butterknife.ButterKnife;

public class SplashFragment extends Fragment {

//    @BindView(R.id.btnMovie)
    Button button;
    private static int SPLASH_TIME_OUT = 2000;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        button.setOnClickListener(v -> {
//            NavDirections action = SplashFragmentDirections.actionMovieFragment();
//            Navigation.findNavController(view).navigate(action);
//        });

        new Handler().postDelayed(() -> {
            NavDirections action = SplashFragmentDirections.actionMovieFragment();
            Navigation.findNavController(view).navigate(action);
        },SPLASH_TIME_OUT);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }
}