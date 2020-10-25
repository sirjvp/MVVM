package com.uc.mvvm.network;

import com.uc.mvvm.model.CastResponse;
import com.uc.mvvm.model.GenreResponse;
import com.uc.mvvm.model.MovieResponse;
import com.uc.mvvm.model.TvShowResponse;

import com.uc.mvvm.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
//    private static Retrofit retrofit;
//
//    public static <S> S createService(Class<S> serviceClass) {
//        if(retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(serviceClass);
//    }

    private ApiEndpoints api;
    private static RetrofitService service;

    private RetrofitService(){
        api = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiEndpoints.class);
    }

    public static RetrofitService getInstance() {
        if(service == null){
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies() {
        return api.getMovies(Constants.API_KEY);
    }
    public Call<TvShowResponse> getTvShows() {
        return api.getTvShows(Constants.API_KEY);
    }
    public Call<GenreResponse> getGenres(String type, int id) {
        return api.getGenres(type, id, Constants.API_KEY);
    }
    public Call<CastResponse> getCast(String type, int id) {
        return api.getCasts(type, id, Constants.API_KEY);
    }
}
