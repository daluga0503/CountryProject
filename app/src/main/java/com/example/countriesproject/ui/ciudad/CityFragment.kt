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
import com.example.countriesproject.data.db.CountryCity
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.databinding.FragmentCiudadBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityFragment : Fragment() {
    private lateinit var binding: FragmentCiudadBinding
    private val viewModel: CityViewModel by viewModels()


    // cuando se infla la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCiudadBinding.inflate(inflater, container, false)
        return binding.root
    }

    // cuando la vista del fragmento ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // navegación hacia el anterior fragmento
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        var selectedCountry = ""

        // Observar cambios en la lista de países y actualizar el ArrayAdapter
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

                binding.paisInput.setOnItemClickListener { _, _, position, _ ->
                    selectedCountry = adapter.getItem(position).toString()
                }
            }
        }

        /*
        * creo una instancia de city con los datos recogidos y creo ese registro
        */
        binding.createBoton.setOnClickListener {
            val city = City(
                0,
                binding.ciudadInput.text.toString(),
                //binding.paisInput.text.toString()
                selectedCountry
            )
            viewModel.createCity(city)
            findNavController().popBackStack()
        }
    }
}
