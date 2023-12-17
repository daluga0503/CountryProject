package com.example.countriesproject.data.api


import com.example.countriesproject.data.db.CountryEntity
import com.google.gson.annotations.SerializedName

// modelo para los datos de un pais
data class CountriesApiModel(
    val common:String,
    val flagUrl:String,
    val region:String,
    val population:Int
)

// modelo para una lista de paises
data class CountriesListApiModel(
    val list:List<CountriesApiModel>
)


data class CountriesDetailResponse(
    val name:PaisNameResponse,
    val flags: PaisFlagsResponse,
    val region:String,
    val population:Int
){
    fun asApiModel(): CountriesListApiModel {
        return CountriesListApiModel(
            list = listOf(
                CountriesApiModel(
                    common = name.common,
                    flagUrl = flags.png,
                    region = region,
                    population = population
                )
            )
        )
    }
}


data class PaisNameResponse(
    @SerializedName("common")
    val common: String
)

data class PaisFlagsResponse(
    @SerializedName("png")
    val png:String
)

// Convierte la lista de paises a una lista de la entidad pais
fun List<CountriesListApiModel>.asEntityModel(): List<CountryEntity> {
    return this.flatMap { countriesListApiModel ->
        countriesListApiModel.list.map { countryApiModel ->
            CountryEntity(
                name = countryApiModel.common,
                flag = countryApiModel.flagUrl,
                region = countryApiModel.region,
                population = countryApiModel.population
            )
        }
    }
}
