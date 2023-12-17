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

    //emite la lista de paises desde la bbdd y los mapea a tipo Country
    val country: Flow<List<Country>>

        get() {
            val list = dbRepository.allCountry.map {
                it.asCountry()
            }
            return list
        }

    // obtengo los detalles de un pais por su nombre desde la bbdd
    suspend fun getName(name:String):CountryEntity{
        return dbRepository.getCountryDetail(name)
    }

    // obtengo el flujo de la lista de todos los paises desde la bbdd
    suspend fun getAllCountries(): Flow<List<Country>> {
        return dbRepository.getAllCountries().map { it.asCountry() }
    }

    // actualizar la lista de paises desde la api y se almecena en la bbdd
    suspend fun refreshList(){
        withContext(Dispatchers.IO){
            val apiCountry = apiRepository.getAll()
            dbRepository.insert(apiCountry.asEntityModel())
        }
    }



    //Ciudades


    //emite la lista de ciudades desde la bbdd y los mapea a tipo City
    val city: Flow<List<City>>
        get() {
            val list = dbRepository.allCity.map {
                it.asCity()
            }
            return list
        }


    // creo una ciudad en la base de datos. el hilo donde se ejecuta es en la de entrada y salida
    suspend fun createCity(city: City){
        withContext(Dispatchers.IO){
            val city = CityEntity(
                city.listaVisitadosId,
                city.nameCiudad,
                city.namePais,
            )
            dbRepository.insertCity(city)
        }
    }
}