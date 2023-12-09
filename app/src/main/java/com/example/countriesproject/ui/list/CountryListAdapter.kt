package com.example.countriesproject.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.request.ImageRequest
import com.example.countriesproject.data.repository.Country
import com.example.countriesproject.databinding.CountryListItemBinding
import android.content.Context
import android.view.View
import coil.imageLoader

class CountryListAdapter(private val context: Context, private val onClick:((View, Country) ->Unit)): ListAdapter<Country, CountryListAdapter.CountryViewHolder>(DiffCallback){

    inner class CountryViewHolder(private val binding:CountryListItemBinding, private val onClick: (View, Country) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bindCountry(country:Country){
            binding.countryName.text = country.name
            val imgRequest = ImageRequest.Builder(context)
                .data(country.flag)
                .crossfade(true)
                .target(binding.countryImg)
                .build()
            context.imageLoader.enqueue(imgRequest)
            binding.countryCard.setOnClickListener {
                onClick(it, country)
            }
        }
    }



    object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }


        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val  binding = CountryListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return CountryViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) = holder.bindCountry(getItem(position))
}