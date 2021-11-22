package com.nedashkivskyi.numbers_fact.data.db

import androidx.room.*
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity

@Dao
interface DaoNumbersData {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(numbersData: NumbersDataEntity)

    @Delete
    suspend fun delete(numbersData: NumbersDataEntity)

    @Update
    suspend fun update(numbersData: NumbersDataEntity)

    @Query("SELECT * from numbers ORDER BY id DESC")
    suspend fun getAllNumbers(): List<NumbersDataEntity>

    @Query("SELECT * from numbers WHERE :number = number")
    suspend fun getNumbersByNumber(number: Int): List<NumbersDataEntity>

    @Query("SELECT * from numbers WHERE :id = id")
    suspend fun getNumberById(id: Int): NumbersDataEntity
}