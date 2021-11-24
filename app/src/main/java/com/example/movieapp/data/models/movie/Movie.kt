package com.example.movieapp.data.models.movie


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Search")
    val search: List<MovieResponse>?,
    @SerializedName("totalResults")
    val totalResults: String?
)