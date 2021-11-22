package com.nedashkivskyi.numbers_fact.utils

import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity
import com.nedashkivskyi.numbers_fact.data.model.remote.NumbersDataDto

object Converter {
    fun fromNumbersDataDtoToNumbersDataEntity(
        numbers: NumbersDataDto
    ): NumbersDataEntity {
        return NumbersDataEntity(id = 0,
            text = numbers.text,
            number = numbers.number,
            found = numbers.found,
            type = numbers.type)
    }
}

