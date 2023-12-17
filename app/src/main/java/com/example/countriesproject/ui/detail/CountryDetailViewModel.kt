package com.example.countriesproject.ui.detail


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.countriesproject.data.db.CountryEntity
import com.example.countriesproject.data.repository.Country
import com.example.countriesproject.data.repository.CountryRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@HiltViewModel
class CountryDetailViewModel @Inject constructor(private val repository: CountryRepository): ViewModel() {
    // LiveData para representar los detalles del país
    private val _countryDetail = MutableLiveData<CountryEntity>()
    val countryDetail: LiveData<CountryEntity> = _countryDetail


    fun countryDetail(name: String){
        // operciones asincronas
        viewModelScope.launch{
            // obtengo los detalles del pais
            val detalles = repository.getName(name)
            // actualizo el LiveData con los detalles obtenidos
            _countryDetail.value = detalles
        }
    }
}