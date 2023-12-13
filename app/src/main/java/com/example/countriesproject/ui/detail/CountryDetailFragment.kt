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



    val observer = Observer<CountryEntity>{
        binding.toolbar.setNavigationOnClickListener{
            findNavController().popBackStack(R.id.countryListFragment, false)
        }
        binding.countryImg.load(it.flag)
        binding.countryName.text = it.name
        binding.countryPopulation.text = it.population.toString()
        binding.countryRegion.text = it.region
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
        viewModel.countryDetail(args.name)

        viewModel.countryDetail.observe(viewLifecycleOwner,observer)
    }

}