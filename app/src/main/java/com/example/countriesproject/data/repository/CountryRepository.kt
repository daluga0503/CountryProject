package com.example.countriesproject.data.repository

import com.example.countriesproject.data.api.CountriesApiRepository
import com.example.countriesproject.data.api.asEntityModel
import com.example.countriesproject.data.db.CityEntity
import com.example.countriesproject.data.db.CountryDBRepository
import com.example.countriesproject.data.db.CountryEntity
import com.example.countriesproject.data.db.asCity
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

    suspend fun getName(name:String):CountryEntity{
        return dbRepository.getCountryDetail(name)
    }

    suspend fun getAllCountries(): Flow<List<Country>> {
        return dbRepository.getAllCountries().map { it.asCountry() }
    }


    suspend fun refreshList(){
        withContext(Dispatchers.IO){
            val apiCountry = apiRepository.getAll()
            dbRepository.insert(apiCountry.asEntityModel())
        }
    }



    //Ciudades

    val city: Flow<List<City>>
        get() {
            val list = dbRepository.allCity.map {
                it.asCity()
            }
            return list
        }

    suspend fun createCity(city: City){
        withContext(Dispatchers.IO){
            val city = CityEntity(
                city.listaVisitadosId,
                city.namePais,
                city.nameCiudad
            )
            dbRepository.insertCity(city)
        }
    }
}