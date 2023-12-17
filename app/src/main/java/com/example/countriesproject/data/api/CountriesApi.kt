package com.example.countriesproject.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton


// interfaz donde defino las relaciones de la api
interface CountryApi {
    // solicitud get para obtener todos los paises de la api
    @GET("v3.1/all")
    suspend fun fetchAllPais():List<CountriesDetailResponse>
}
// la anotación singleton significa que solo hay una instancia única de esta clase en la app
@Singleton
class CountryService @Inject constructor() {
    // creo la instancia de la interfaz
    private val retrofit = Retrofit.Builder()
        // especifico la url base de la api
        .baseUrl("https://restcountries.com/")
        // convertidor para hacer la conversión entre las JSON y Kotlin
        .addConverterFactory(GsonConverterFactory.create())
        // construyo la instancia de retrofit
        .build()
    // creo una instancia de la interfaz usando retrofit
    val api:CountryApi = retrofit.create(CountryApi::class.java)
}