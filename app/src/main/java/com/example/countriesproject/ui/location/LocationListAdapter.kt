package com.example.countriesproject.ui.location

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.databinding.CityListItemBinding

class LocationListAdapter (private val context: Context, val onShare:((c:City, v: View)->Unit)): ListAdapter<City, LocationListAdapter.LocationViewHolder>(DiffCallback) {

    inner class LocationViewHolder(private val binding: CityListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindCity(city: City){
            binding.cityName.text = city.nameCiudad
            binding.countryName.text = city.namePais
            binding.compartirButton.setOnClickListener{
                onShare(city, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListAdapter.LocationViewHolder {
        val  binding = CityListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationListAdapter.LocationViewHolder, position: Int) = holder.bindCity(getItem(position))

    object DiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.nameCiudad == newItem.nameCiudad
        }


        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem==newItem
        }

    }
}