package com.example.countriesproject.ui.location

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.countriesproject.R
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.databinding.FragmentLocationListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationListFragment : Fragment() {
    private lateinit var binding: FragmentLocationListBinding
    private val viewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LocationListAdapter(requireContext()) { city, view ->
            onShareItem(city)
        }
        binding.visit.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.submitList(it.city)
                }
            }
        }

        binding.floatingbutton.setOnClickListener {
            val action = LocationListFragmentDirections.actionLocationListFragmentToCiudadFragment()
            view.findNavController().navigate(action)
        }

    }
    private fun onShareItem(city: City) {
        // Guardo las info de la ciudad en variables
        val cityName = city.nameCiudad
        val countyName = city.namePais

        // Crea el mensaje de texto para compartir
        val statusText = getString(R.string.shareInfo, cityName, countyName)

        // Crea un intent para compartir la información
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, statusText)
            type = "text/plain"
        }

        // Inicia la actividad de compartir
        startActivity(Intent.createChooser(intent, ""))
    }
}