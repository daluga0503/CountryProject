package com.example.countriesproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listCountryEntity: List<CountryEntity>)

    @Query("SELECT * FROM country")
    fun getAll(): Flow<List<CountryEntity>>
}