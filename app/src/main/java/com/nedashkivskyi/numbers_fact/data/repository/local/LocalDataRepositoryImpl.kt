package com.nedashkivskyi.numbers_fact.data.repository.local

import com.nedashkivskyi.numbers_fact.data.db.DataBaseNumbers
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(
    private val dataBase: DataBaseNumbers
): LocalDataRepository{

    private val dao = dataBase.daoNumbersData()

    override suspend fun insert(numbersData: NumbersDataEntity) =
        dao.insert(numbersData = numbersData)

    override suspend fun delete(numbersData: NumbersDataEntity) =
        dao.delete(numbersData = numbersData)

    override suspend fun update(numbersData: NumbersDataEntity) =
        dao.update(numbersData = numbersData)

    override suspend fun getAllNumbers(): List<NumbersDataEntity> =
        dao.getAllNumbers()

    override suspend fun getNumbersByNumber(number: Int): List<NumbersDataEntity> =
        dao.getNumbersByNumber(number = number)

    override suspend fun getNumberById(id: Int): NumbersDataEntity =
        dao.getNumberById(id = id)
}