package com.example.countriesproject.ui.location

import com.example.countriesproject.data.repository.City

data class LocationUiState(
    val city: List<City>,
    val errorMessage:String?=null,
)
