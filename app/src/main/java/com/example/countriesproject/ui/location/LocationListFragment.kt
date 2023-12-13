package com.example.countriesproject.ui.location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.countriesproject.R
import com.example.countriesproject.databinding.FragmentLocationListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationListFragment : Fragment() {
    private lateinit var binding: FragmentLocationListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding = FragmentLocationListBinding.inflate(inflater,container,false)
        binding  = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val navController = findNavController()
        binding.floatingButton.setOnClickListener{
            navController.navigate(R.id.action_locationListFragment_to_ciudadFragment)
        }*/
        binding.floatingbutton.setOnClickListener {
            var action = LocationListFragmentDirections.actionLocationListFragmentToCiudadFragment()
            view.findNavController().navigate(action)
        }


    }
}