package com.nedashkivskyi.numbers_fact.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NumbersDataDto(
    @SerialName("text")
    var text: String,
    @SerialName("number")
    var number: Int,
    @SerialName("found")
    var found: Boolean,
    @SerialName("type")
    var type: String
)