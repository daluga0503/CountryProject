package com.example.countriesproject.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesproject.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.io.IOException

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: CountryRepository): ViewModel(){
    private val _uiState = MutableStateFlow(LocationUiState(listOf()))
    val uiState: StateFlow<LocationUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
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
}
