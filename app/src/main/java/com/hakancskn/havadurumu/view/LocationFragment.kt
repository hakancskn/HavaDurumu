package com.hakancskn.havadurumu.view

import android.os.Bundle
import android.view.*
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakancskn.havadurumu.R
import com.hakancskn.havadurumu.adapter.ForecastAdapter
import com.hakancskn.havadurumu.model.Forecasts
import com.hakancskn.havadurumu.util.locationToLocationQuery
import com.hakancskn.havadurumu.viewmodel.LocationViewModel
import kotlinx.android.synthetic.main.fragment_location.*


class LocationFragment : Fragment() {

    private lateinit var viewModel: LocationViewModel
    private val forecastAdapter = ForecastAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.location_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            val action = LocationFragmentDirections.actionLocationFragmentToSearchFragment()
            this.view?.let { Navigation.findNavController(it).navigate(action) }
            return true
        }
        return super.onOptionsItemSelected(item)
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

        forecast_list.layoutManager = LinearLayoutManager(context)
        forecast_list.adapter = forecastAdapter

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
                head_line.text = forecasts.headLine!!.text
                forecast_list.visibility = View.VISIBLE
                forecastAdapter.updateForecastList(forecasts.dailyForecasts)
            }
        }
        )

        viewModel.locationError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    locationError.visibility = View.VISIBLE
                } else {
                    locationError.visibility = View.GONE
                }
            }
        })

        viewModel.locationLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    locationLoading.visibility = View.VISIBLE

                    locationError.visibility = View.GONE
                } else {
                    locationLoading.visibility = View.GONE
                }
            }
        })


    }


}