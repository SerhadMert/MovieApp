package com.example.movieapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.moviedetail.MovieDetailResponse
import com.example.movieapp.data.repository.Repository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject constructor(private var repository: Repository): ViewModel(){

    fun getMovieById(id: String): LiveData<Resource<MovieDetailResponse>>{
        return repository.getMovieByImdbId(id)
    }
}