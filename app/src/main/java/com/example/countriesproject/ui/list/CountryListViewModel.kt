package com.example.countriesproject.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesproject.data.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val repository: CountryRepository):ViewModel() {
    private val _uiState = MutableStateFlow(CountryListUiState(listOf()))
    val uiState: StateFlow<CountryListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.refreshList()
            }
            catch (e:IOException){
                _uiState.value = _uiState.value.copy(errorMessage = e.message!!)
            }
        }

        viewModelScope.launch {
            repository.country.collect {
                _uiState.value = CountryListUiState(it)
            }
        }
    }
}