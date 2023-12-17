package com.example.countriesproject.data.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryDBRepository @Inject constructor(private val countryDao: CountryDao) {

    // emite la lista de todos los paises a la bbdd
    val allCountry: Flow<List<CountryEntity>> = countryDao.getAll()


            //insertar una lista de entidades de país en la base de datos
            @WorkerThread
            suspend fun insert(listCountryEntity: List<CountryEntity>){
                countryDao.insert(listCountryEntity)
            }

            // emite la lista de los paises desde la bbdd
            suspend fun getAllCountries(): Flow<List<CountryEntity>> {
                return countryDao.getAll()
            }


            // detalles de un pais por su nombre
            suspend fun getCountryDetail(name:String):CountryEntity{
                return countryDao.getCountryDetail(name);
            }

            //Ciudades

            // emite la lista de las ciudades desde la bbdd
            val allCity: Flow<List<CityEntity>> = countryDao.getAllCity()

            // creacion de un regsitro en la tabla ciudad
            suspend fun insertCity(cityEntity: CityEntity){
                countryDao.insertCity(cityEntity)
            }
}