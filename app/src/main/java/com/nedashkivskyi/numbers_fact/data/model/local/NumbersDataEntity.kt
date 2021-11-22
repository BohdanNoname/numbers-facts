package com.nedashkivskyi.numbers_fact.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumbersDataEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "number")
    var number: Int,
    @ColumnInfo(name = "found")
    var found: Boolean,
    @ColumnInfo(name = "type")
    var type: String
)