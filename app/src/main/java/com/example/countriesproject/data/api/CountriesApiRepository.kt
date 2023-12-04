package com.example.countriesproject.data.api

import javax.inject.Inject
import javax.inject.Singleton

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