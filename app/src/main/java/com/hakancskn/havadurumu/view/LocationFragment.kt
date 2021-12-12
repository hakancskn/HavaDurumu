package com.hakancskn.havadurumu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hakancskn.havadurumu.R
import com.hakancskn.havadurumu.model.Forecasts
import com.hakancskn.havadurumu.util.locationToLocationQuery
import com.hakancskn.havadurumu.viewmodel.LocationViewModel


class LocationFragment : Fragment() {

    private lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel = ViewModelProvider(requireActivity()).get(LocationViewModel::class.java)
        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer { location ->
            location?.let {

                viewModel.getLocationKey(query = locationToLocationQuery(location))
            }
        })

        viewModel.locationKeyLiveData.observe(viewLifecycleOwner, Observer { locationKey ->
            locationKey?.let {
              viewModel.getForecasts(locationKey = locationKey)
            }
        }
        )

        viewModel.forecastsLiveData.observe(viewLifecycleOwner, Observer { forecasts ->
            forecasts?.let {
               prepareUI(forecasts)
            }
        }
        )


    }

    private fun prepareUI(forecasts: Forecasts){

    }


}