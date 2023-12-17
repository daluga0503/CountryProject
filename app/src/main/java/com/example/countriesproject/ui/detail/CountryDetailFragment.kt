package com.example.countriesproject.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.countriesproject.R
import com.example.countriesproject.data.db.CountryEntity
import com.example.countriesproject.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryDetailFragment : Fragment() {
    private val viewModel:CountryDetailViewModel by viewModels()
    private val args: CountryDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCountryDetailBinding


    // Observer para observar cambios en los detalles del país en el ViewModel
    val observer = Observer<CountryEntity>{
        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack(R.id.countryListFragment, false)
        }
        // cargar la imagen de la bandera utilizando coil
        binding.countryImg.load(it.flag)
        binding.countryName.text = getString(R.string.detailPais, it.name)
        binding.countryPopulation.text = getString(R.string.detailPoblacion, it.population.toString())
        binding.countryRegion.text = getString(R.string.detailContinente, it.region)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }

        // Obtener y observar los detalles del país desde el ViewModel
        viewModel.countryDetail(args.name)
        viewModel.countryDetail.observe(viewLifecycleOwner,observer)
    }

}