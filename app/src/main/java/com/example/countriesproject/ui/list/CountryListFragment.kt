package com.example.countriesproject.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.countriesproject.data.repository.Country
import com.example.countriesproject.databinding.FragmentPaisListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountryListFragment:Fragment() {
    private lateinit var binding: FragmentPaisListBinding
    private val viewModel:CountryListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaisListBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // dapatador para la lista de paises
        val adapter = CountryListAdapter(requireContext()) { view, country->
            // Defino la acción de navegación al hacer clic en un país
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(country.name)
            view.findNavController().navigate(action)
        }
        // comunico al recyclerView que ulitice el adaptador para mostrar los elementos de la lista
        val rv = binding.countryList
        rv.adapter = adapter

        // cuandoel estado de la interfaz cambia , cuando carga los paises, el adapter se actualiza mostrando la lista
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.submitList(it.country)
                }
            }
        }
    }
}