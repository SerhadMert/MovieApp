package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.ApiDataSource
import com.example.movieapp.utils.performNetworkOperation
import javax.inject.Inject

class Repository
@Inject constructor(private var apiDataSource: ApiDataSource){

    fun getMoviesBySearch(title: String,page: Int) = performNetworkOperation {

        apiDataSource.getMoviesByQuery(title,page)
    }

    fun getMovieByImdbId(id: String) = performNetworkOperation {
        apiDataSource.getMovieByImdbId(id)
    }
}