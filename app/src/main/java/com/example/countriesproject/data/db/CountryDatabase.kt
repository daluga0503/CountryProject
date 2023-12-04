package com.example.countriesproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountryDatabase(): RoomDatabase() {
    abstract fun countryDao():CountryDao

    companion object{
        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
            }
        }
        private fun buildDatabase(context: Context):CountryDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                CountryDatabase::class.java,
                "country_db"
            ).build()
        }
    }
}