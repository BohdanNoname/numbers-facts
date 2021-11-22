package com.nedashkivskyi.numbers_fact.data.repository.remote

import com.nedashkivskyi.numbers_fact.data.api.ApiService
import com.nedashkivskyi.numbers_fact.data.model.remote.NumbersDataDto
import com.nedashkivskyi.numbers_fact.utils.Result
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataRepository {

    override suspend fun getNumber(number: String): Result<NumbersDataDto> {
        val json: String = ""

        return try {
            val response = apiService.getNumber(number = number, json = json)
            val result = response.body()

            if (response.isSuccessful && result != null){
                    Result.Success(result)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception){
            Result.Error(e.message?:"An error occurred")
        }
    }

    override suspend fun getRandomNumber(): Result<NumbersDataDto> {
        val json: String = ""

        return try {
            val response = apiService.getRandomNumber(json = json)
            val result = response.body()

            if (response.isSuccessful && result != null){
                Result.Success(result)
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception){
            Result.Error(e.message?:"An error occurred")
        }
    }
}