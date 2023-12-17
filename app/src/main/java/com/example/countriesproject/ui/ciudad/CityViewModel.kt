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

    // MutableStateFlow para representar el estado de la interfaz de usuario de Location
    private val _uiState = MutableStateFlow(LocationUiState(listOf()))

    // StateFlow expuesto para que las partes externas observen el estado de la interfaz de usuario
    val uiState: StateFlow<LocationUiState> get() = _uiState.asStateFlow()


    // almaceno la lista de paises
    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    // observable para observar la lista de paises
    val allCountries: StateFlow<List<Country>> get() = _allCountries

    init {
        viewModelScope.launch {
            // Obtener la lista inicial de países y actualizar el _allCountries StateFlow
            repository.getAllCountries().collect { countries ->
                _allCountries.value = countries
            }

            try {
                repository.refreshList()
            } catch (e: IOException) {
                // Handle exception
            }
            // Observar la colección de ciudades desde el repositorio y actualizar el estado de la interfaz de usuario
            repository.city.collect {
                _uiState.value = LocationUiState(it)
            }
        }
    }

    // metodo para crear un registro de city
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