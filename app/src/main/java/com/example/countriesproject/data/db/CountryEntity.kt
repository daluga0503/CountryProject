package com.example.countriesproject.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
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
