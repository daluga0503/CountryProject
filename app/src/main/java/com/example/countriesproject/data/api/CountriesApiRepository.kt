package com.example.countriesproject.data.api

import javax.inject.Inject
import javax.inject.Singleton


/*
* Obtengo la lista de paises y mapeo la respuesta de la api por paises y esos paises los voy añadiendo a la lista.
* Devuelvo la lista que he ido generando.
*/
@Singleton
class CountriesApiRepository @Inject constructor(private val service: CountryService) {
    suspend fun getAll(): List<CountriesListApiModel> {
        val list = service.api.fetchAllPais()
        val countriesApiModel = list.map {
            it.asApiModel()
        }
        return countriesApiModel
    }
}