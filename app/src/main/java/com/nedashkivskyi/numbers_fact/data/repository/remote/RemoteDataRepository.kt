package com.nedashkivskyi.numbers_fact.data.repository.remote

import com.nedashkivskyi.numbers_fact.data.model.remote.NumbersDataDto
import com.nedashkivskyi.numbers_fact.utils.Result

interface RemoteDataRepository {
    suspend fun getNumber(number: String): Result<NumbersDataDto>
    suspend fun getRandomNumber(): Result<NumbersDataDto>
}