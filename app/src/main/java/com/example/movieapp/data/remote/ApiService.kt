package com.example.movieapp.data.remote

import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.data.models.moviedetail.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    suspend fun getMoviesByQuery(
        @Query("apikey") apiKey: String,
        @Query("s") title: String,
        @Query("page") page: Int
    ): Response<Movie>

    @GET("/")
    suspend fun getMovieByImdbId(
        @Query("i") id: Int
    ): Response<MovieDetailResponse>
}