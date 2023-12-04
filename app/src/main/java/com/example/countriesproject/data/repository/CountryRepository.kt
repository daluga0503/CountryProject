package com.example.countriesproject.data.repository

import com.example.countriesproject.data.api.CountriesApiRepository
import com.example.countriesproject.data.api.asEntityModel
import com.example.countriesproject.data.db.CountryDBRepository
import com.example.countriesproject.data.db.asCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val dbRepository: CountryDBRepository,
    private val apiRepository: CountriesApiRepository
) {
    val country: Flow<List<Country>>

        get() {
            val list = dbRepository.allCountry.map {
                it.asCountry()
            }
            return list
        }

    suspend fun refreshList(){
        withContext(Dispatchers.IO){
            val apiCountry = apiRepository.getAll()
            dbRepository.insert(apiCountry.asEntityModel())
        }
    }
}