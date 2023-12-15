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
    private val _countryDetail = MutableLiveData<CountryEntity>()
    val countryDetail: LiveData<CountryEntity> = _countryDetail


    fun countryDetail(name: String){
        Log.d("Daniel", countryDetail.toString())
        viewModelScope.launch{
            val detalles = repository.getName(name)
            _countryDetail.value = detalles
        }
    }
}