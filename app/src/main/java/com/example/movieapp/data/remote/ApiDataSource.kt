package com.example.movieapp.data.remote

import com.example.movieapp.utils.BaseDataSource
import javax.inject.Inject

class ApiDataSource
@Inject constructor(private val apiService: ApiService): BaseDataSource(){

    suspend fun getMoviesByQuery(title: String,page: Int) = getResult {
        apiService.getMoviesByQuery(title,page)
    }

    suspend fun getMovieByImdbId(id: String) = getResult {
        apiService.getMovieByImdbId(id)
    }
}