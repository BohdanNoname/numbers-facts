package com.nedashkivskyi.numbers_fact.data.repository.local

import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity

interface LocalDataRepository {
    suspend fun insert(numbersData: NumbersDataEntity)
    suspend fun delete(numbersData: NumbersDataEntity)
    suspend fun update(numbersData: NumbersDataEntity)
    suspend fun getAllNumbers(): List<NumbersDataEntity>
    suspend fun getNumbersByNumber(number: Int): List<NumbersDataEntity>
    suspend fun getNumberById(id: Int): NumbersDataEntity
}
