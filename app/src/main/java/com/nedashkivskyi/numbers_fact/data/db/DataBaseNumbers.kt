package com.nedashkivskyi.numbers_fact.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nedashkivskyi.numbers_fact.data.model.local.NumbersDataEntity

@Database(entities = [NumbersDataEntity::class], version = 1, exportSchema = false)
abstract class DataBaseNumbers: RoomDatabase() {

    abstract fun daoNumbersData(): DaoNumbersData

    companion object{
        @Volatile
        private var INSTANCE: DataBaseNumbers? = null

        fun getDataBase(context: Context): DataBaseNumbers{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseNumbers::class.java,
                    "database_exchange_rate"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}