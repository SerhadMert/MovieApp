package com.example.movieapp.data.remote

import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.data.models.moviedetail.MovieDetailResponse
import com.example.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMoviesByQuery(
        @Query("s") title: String,
        @Query("page") page: Int
    ): Response<Movie>

    @GET(Constants.END_POINT)
    suspend fun getMovieByImdbId(
        @Query("i") id: String
    ): Response<MovieDetailResponse>
}