package com.example.countriesproject.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.data.repository.Country

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey
    val name:String,
    val flag:String,
    val population:Int,
    val region:String,
    val visited:Boolean=false
)

@Entity(tableName = "ciudades")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val listaVisitadosId : Int,
    val namePais: String,
    val nameCiudad: String

)

data class CountryCity(
    @Embedded val country:  CountryEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "namePais"
    )
    val visitados : List<CityEntity>
)

fun List<CountryEntity>.asCountry():List<Country>{
    return this.map {
        Country(it.name.replaceFirstChar { c ->c.uppercase() },
            it.flag,
            it.population,
            it.region,
            it.visited
            )
    }
}

fun List<CityEntity>.asCity(): List<City>{
    return this.map {
        City(
            it.listaVisitadosId,
            it.namePais,
            it.nameCiudad
        )
    }
}
