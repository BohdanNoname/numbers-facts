package com.nedashkivskyi.numbers_fact.data.repository

import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import com.nedashkivskyi.numbers_fact.data.model.remote.NumbersDataDto
import com.nedashkivskyi.numbers_fact.data.repository.local.LocalDataRepositoryImpl
import com.nedashkivskyi.numbers_fact.data.repository.remote.RemoteDataRepositoryImpl
import com.nedashkivskyi.numbers_fact.utils.Converter
import com.nedashkivskyi.numbers_fact.utils.EventState
import com.nedashkivskyi.numbers_fact.utils.Result
import javax.inject.Inject

class MainDataSourceRepository @Inject constructor(
    private val remote: RemoteDataRepositoryImpl,
    private val local: LocalDataRepositoryImpl
){

    suspend fun fetchNumberData(number: String): EventState<List<NumbersDataEntity>>{
        return when(val result = remote.getNumber(number = number)){
            is Result.Success ->{
                local.insert(Converter.fromNumbersDataDtoToNumbersDataEntity(result.data!!))
                EventState.Success(data = local.getAllNumbers())
            }
            is Result.Error ->
                EventState.Error(message = result.message)
        }
    }

    suspend fun fetchRandomNumberData(): EventState<List<NumbersDataEntity>>{
        return when(val result = remote.getRandomNumber()){
            is Result.Success ->{
                local.insert(Converter.fromNumbersDataDtoToNumbersDataEntity(result.data!!))

                EventState.Success(data = local.getAllNumbers())
            }

            is Result.Error ->
                EventState.Error(message = result.message)
        }
    }

    suspend fun fetchAllNumberHistory(): EventState<List<NumbersDataEntity>>{
        val result = local.getAllNumbers()
        return if (result.isNotEmpty()){
            EventState.Success(data = result)
        } else{
            EventState.Empty()
        }
    }
}