package com.example.countriesproject.ui.ciudad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.countriesproject.R
import com.example.countriesproject.data.db.CountryEntity
import com.example.countriesproject.databinding.FragmentCiudadBinding
import com.example.countriesproject.ui.detail.CountryDetailFragmentArgs
import com.example.countriesproject.ui.detail.CountryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CiudadFragment : Fragment() {
    private lateinit var binding: FragmentCiudadBinding

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
    }
}
