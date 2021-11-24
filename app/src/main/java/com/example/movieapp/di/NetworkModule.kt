package com.example.movieapp.di

import com.example.movieapp.data.remote.ApiDataSource
import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        baseUrl: BaseUrl
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson{
        return  Gson()
    }

    @Provides
    fun provideApiDataSource(
        apiService: ApiService
    ): ApiDataSource{
        return ApiDataSource(apiService)
    }

    @Provides
    fun provideBaseUrl(): BaseUrl{
        return BaseUrl(Constants.BASE_URL)
    }
}

data class BaseUrl(val url: String)


