package com.example.countriesproject.ui.list

import com.example.countriesproject.data.repository.Country

data class CountryListUiState(
    val country:List<Country>,
    val errorMessage:String?=null,
)
