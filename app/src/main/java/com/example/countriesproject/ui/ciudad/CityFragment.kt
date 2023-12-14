package com.example.countriesproject.ui.ciudad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.countriesproject.data.repository.City
import com.example.countriesproject.databinding.FragmentCiudadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment() {
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
    }
}
