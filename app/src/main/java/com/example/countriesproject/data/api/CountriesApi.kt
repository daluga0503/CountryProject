package com.example.countriesproject.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface CountryApi {
    @GET("v3.1/all")
    suspend fun fetchAllPais():List<CountriesDetailResponse>
    @GET("v3/name{name}/")
    suspend fun getDetail(@Path("name") name:String):List<CountriesDetailResponse>
}
@Singleton
class CountryService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:CountryApi = retrofit.create(CountryApi::class.java)
}