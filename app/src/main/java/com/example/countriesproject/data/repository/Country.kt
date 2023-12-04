package com.example.countriesproject.data.repository

import com.example.countriesproject.data.api.PaisFlagsResponse

data class Country(
    val name:String,
    val flag:String,
    val population:Int,
    val region:String,
    val visited:Boolean
)
