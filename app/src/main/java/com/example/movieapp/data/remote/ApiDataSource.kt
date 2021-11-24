package com.example.movieapp.data.remote

import com.example.movieapp.utils.BaseDataSource
import javax.inject.Inject

class ApiDataSource
@Inject constructor(private val apiService: ApiService): BaseDataSource(){

    suspend fun getMoviesByQuery(apiKey: String,title: String,page: Int) = getResult {
        apiService.getMoviesByQuery(apiKey,title,page)
    }

    suspend fun getMovieByImdbId(id: Int) = getResult {
        apiService.getMovieByImdbId(id)
    }
}