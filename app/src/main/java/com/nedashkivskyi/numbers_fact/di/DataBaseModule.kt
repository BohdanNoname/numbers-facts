package com.nedashkivskyi.numbers_fact.di

import android.content.Context
import com.nedashkivskyi.numbers_fact.data.db.DataBaseNumbers
import com.nedashkivskyi.numbers_fact.data.repository.local.LocalDataRepository
import com.nedashkivskyi.numbers_fact.data.repository.local.LocalDataRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Provides


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDataBaseNumbersData(@ApplicationContext context: Context): DataBaseNumbers =
        DataBaseNumbers.getDataBase(context = context)

    @Provides
    @Singleton
    fun providesLocalDataRepository(dataBaseNumbers: DataBaseNumbers): LocalDataRepository =
        LocalDataRepositoryImpl(dataBase = dataBaseNumbers)
}