package com.nedashkivskyi.numbers_fact.di

import com.nedashkivskyi.numbers_fact.data.api.ApiService
import com.nedashkivskyi.numbers_fact.data.repository.remote.RemoteDataRepositoryImpl
import com.nedashkivskyi.numbers_fact.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesApiService(): ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.NumbersApiBaseUrl)
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRemoteDataSource(apiService: ApiService): RemoteDataRepositoryImpl =
        RemoteDataRepositoryImpl(apiService = apiService)
}