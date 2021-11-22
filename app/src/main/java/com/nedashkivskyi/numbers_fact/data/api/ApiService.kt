package com.nedashkivskyi.numbers_fact.data.api

import com.nedashkivskyi.numbers_fact.data.model.remote.NumbersDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/{number}/?")
    suspend fun getNumber(
        @Path("number") number: String,
        @Query("json") json: String
    ): Response<NumbersDataDto>

    @GET("random/?")
    suspend fun getRandomNumber(
        @Query("json") json: String
    ):Response<NumbersDataDto>
}