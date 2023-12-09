package com.example.countriesproject.data.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryDBRepository @Inject constructor(private val countryDao: CountryDao) {
    val allCountry: Flow<List<CountryEntity>> = countryDao.getAll()

            @WorkerThread
            suspend fun insert(listCountryEntity: List<CountryEntity>){
                countryDao.insert(listCountryEntity)
            }

            suspend fun getCountryDetail(name:String):CountryEntity{
                return countryDao.getCountryDetail(name);
            }
}