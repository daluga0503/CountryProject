package com.example.countriesproject.ui.ciudad
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.countriesproject.R
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.databinding.FragmentCiudadBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityFragment : Fragment() {
    /*
    private lateinit var binding: FragmentCiudadBinding
    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCiudadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
        binding.createBoton.setOnClickListener{
            val city = City(
                0,
                binding.ciudadInput.text.toString(),
                binding.descriptionInput.text.toString()
            )
            viewModel.createCity(city)
            findNavController().popBackStack()
        }
    }*/
    private lateinit var binding: FragmentCiudadBinding
    private val viewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCiudadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        // Observe changes in the list of countries and update the ArrayAdapter
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.allCountries.collect { countries ->
                val countryNames = countries.map { it.name }
                //Log.d("country", countryNames.toString())
                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_item,
                    countryNames
                )
                binding.paisInput.setAdapter(adapter)
            }
        }
        /*
        binding.createBoton.setOnClickListener {
            val city = City(
                0,
                binding.ciudadInput.text.toString(),
                binding.paisInput.text.toString()
            )
            viewModel.createCity(city)
            findNavController().popBackStack()
        }*/
    }
}
