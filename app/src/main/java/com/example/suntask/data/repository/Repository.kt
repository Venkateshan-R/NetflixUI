package com.example.suntask.data.repository

import com.example.suntask.data.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor() {

    @Inject
    lateinit var apiService: ApiService


    suspend fun getPopularMovies() = apiService.getPopularMovies()

    suspend fun getNowsPlaying() = apiService.getNowsPlaying()

    suspend fun getTopRated() = apiService.getTopRated()
    suspend fun getUpcoming() = apiService.getUpcoming()

    suspend fun getMovieDetails(id : String) = apiService.getMovieDetails(id)

}