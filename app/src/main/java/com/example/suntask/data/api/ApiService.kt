package com.example.suntask.data.api

import com.example.suntask.data.model.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): Response<MoviesModel>

    @GET("3/movie/top_rated")
    suspend fun getTopRated(): Response<MoviesModel>

    @GET("3/movie/now_playing")
    suspend fun getNowsPlaying(): Response<MoviesModel>

    @GET("3/movie/upcoming")
    suspend fun getUpcoming(): Response<MoviesModel>

    @GET("3/movie/{id}/videos")
    suspend fun getMovieDetails(
        @Path(
            value = "id"
        ) id: String
    ): Response<MoviesModel>

}