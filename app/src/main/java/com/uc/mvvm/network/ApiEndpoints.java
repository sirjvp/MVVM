package com.uc.mvvm.network;

import com.uc.mvvm.model.CastResponse;
import com.uc.mvvm.model.GenreResponse;
import com.uc.mvvm.model.MovieResponse;
import com.uc.mvvm.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoints {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowResponse> getTvShows(@Query("api_key") String apiKey);

    @GET("{type}/{id}")
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

//    @GET("movie/{movie_id}")
//    Call<MovieResponse> getDetailMovie(@Path("movie_id") int movieId, @Query("api_key") String apikey)

    @GET("{type}/{id}/credits")
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

}
