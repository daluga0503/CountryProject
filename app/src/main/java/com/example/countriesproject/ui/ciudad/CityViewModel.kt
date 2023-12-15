package com.example.countriesproject.ui.ciudad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesproject.data.db.CountryEntity
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.data.repository.Country
import com.example.countriesproject.data.repository.CountryRepository
import com.example.countriesproject.ui.location.LocationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val repository: CountryRepository): ViewModel() {
    /*
    private val _uiState = MutableStateFlow(LocationUiState(listOf()))
    val uiState: StateFlow<LocationUiState>
        get() = _uiState.asStateFlow()



    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    val allCountries: StateFlow<List<Country>> get() = _allCountries


    init {
        viewModelScope.launch {

            repository.getAllCountries().collect { countries ->
                _allCountries.value = countries
            }
            try {
                repository.refreshList()
            } catch (e: IOException) {
                // Handle exception
            }

            repository.city.collect {
                _uiState.value = LocationUiState(it)
            }

        }
    }






    fun createCity(city: City) {
        viewModelScope.launch {
            try {
                repository.createCity(city)
            }
            catch (e: IOException) {

            }
        }
    }*/
    private val _uiState = MutableStateFlow(LocationUiState(listOf()))
    val uiState: StateFlow<LocationUiState> get() = _uiState.asStateFlow()

    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    val allCountries: StateFlow<List<Country>> get() = _allCountries

    init {
        viewModelScope.launch {
            // Fetch the initial list of countries
            repository.getAllCountries().collect { countries ->
                _allCountries.value = countries
            }

            try {
                repository.refreshList()
            } catch (e: IOException) {
                // Handle exception
            }

            repository.city.collect {
                _uiState.value = LocationUiState(it)
            }
        }
    }

    fun createCity(city: City) {
        viewModelScope.launch {
            try {
                repository.createCity(city)
            } catch (e: IOException) {
                // Handle exception
            }
        }
    }
}