package com.example.countriesproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listCountryEntity: List<CountryEntity>)

    @Query("SELECT * FROM country")
    fun getAll(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country WHERE name = :name")
    suspend fun getCountryDetail(name:String): CountryEntity


    @Transaction
    @Query("SELECT * FROM ciudades")
    fun getCountryWhitVisit(): List<CityEntity>


    //Create datos locales

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCity(cityEntity: CityEntity)

    @Query ("Select * from ciudades")
    fun getAllCity(): Flow<List<CityEntity>>


}