package com.nedashkivskyi.numbers_fact.di

import com.nedashkivskyi.numbers_fact.data.repository.MainDataSourceRepository
import com.nedashkivskyi.numbers_fact.data.repository.local.LocalDataRepositoryImpl
import com.nedashkivskyi.numbers_fact.data.repository.remote.RemoteDataRepositoryImpl
import com.nedashkivskyi.numbers_fact.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = object :
        DispatcherProvider {
        override fun main(): CoroutineDispatcher =
            Dispatchers.Main

        override fun io(): CoroutineDispatcher =
            Dispatchers.IO

        override fun default(): CoroutineDispatcher =
            Dispatchers.Default

        override fun unconfined(): CoroutineDispatcher =
            Dispatchers.Unconfined
    }

    @Provides
    @Singleton
    fun providesMainDataSourceRepository(
        remoteDataRepositoryImpl: RemoteDataRepositoryImpl,
        localDataRepositoryImpl: LocalDataRepositoryImpl
    ): MainDataSourceRepository =
        MainDataSourceRepository(remote = remoteDataRepositoryImpl, local = localDataRepositoryImpl)
}