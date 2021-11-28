package com.example.movieapp.utils

import com.example.movieapp.data.models.movie.Movie
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                val successCallError = Movie("False", null, null)
                return if (body != null && !body.equals(successCallError) ) {
                    Resource.success(body)
                } else {
                    Resource.error("Too many results")
                }
            }
            val errorBody = response.errorBody().toString()
            return error("${response.code()} - $errorBody")
        } catch (err: Exception) {
            return error("${err.localizedMessage} - ${err.message}")
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network error: $message")
    }
}