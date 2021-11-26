package com.example.movieapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.movie.Movie
import com.example.movieapp.data.repository.Repository
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor(private var repository: Repository): ViewModel() {

    var page = 1

    fun getMoviesByQuery(title: String): LiveData<Resource<Movie>>{
        return repository.getMoviesBySearch(title,page)
    }
}