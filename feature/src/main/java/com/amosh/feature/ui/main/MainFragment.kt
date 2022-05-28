package com.amosh.feature.ui.main

import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.amosh.base.BaseFragment
import com.amosh.common.AppMessage
import com.amosh.common.isOffline
import com.amosh.feature.databinding.FragmentMainBinding
import com.amosh.feature.ui.MainViewModel
import com.amosh.feature.ui.contract.MainContract
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Main Fragment
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // globally declare LocationRequest
    private lateinit var locationRequest: LocationRequest

    // globally declare LocationCallback
    private lateinit var locationCallback: LocationCallback


    private val adapter: SpotsAdapter by lazy {
        SpotsAdapter { spot ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(spot)
            findNavController().navigate(action)
        }
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.rvSpots.adapter = adapter
        initObservers()
        getLocationUpdates()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.spotsState) {
                        is MainContract.SpotState.Idle -> {
                            binding.pbProgress.isVisible = false
                        }
                        is MainContract.SpotState.Loading -> {
                            binding.pbProgress.isVisible = adapter.itemCount == 0
                        }
                        is MainContract.SpotState.Success -> {
                            val data = state.spotList
                            adapter.submitList(data)
                            binding.emptyState.isVisible = data.isNullOrEmpty()
                            binding.pbProgress.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Effect.ShowError -> {
                            if (requireContext().isOffline()) {
                                showAppMessage(
                                    AppMessage(
                                        AppMessage.FAILURE,
                                        "No internet connection, please try again later"
                                    )
                                )
                            } else {
                                showAppMessage(
                                    AppMessage(
                                        AppMessage.FAILURE,
                                        it.message ?: "no message"
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getSpotsList(location: Location) {
        viewModel.setEvent(
            MainContract.Event.OnFetchSpotsList(
                lat = location.latitude,
                lng = location.longitude
            )
        )
    }

    private fun getLocationUpdates() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationRequest = LocationRequest.create().apply {
            interval = 50000
            fastestInterval = 50000
            smallestDisplacement = 1000f // 1 km
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.locations.isNotEmpty()) {
                    // get latest location
                    val location = locationResult.lastLocation
                    // use your location object
                    // get latitude , longitude and other info from this
                    getSpotsList(location)
                }
            }
        }
        startLocationUpdates()
    }

    //start location updates
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    // stop location updates
    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // stop receiving location update when activity not visible/foreground
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

}



