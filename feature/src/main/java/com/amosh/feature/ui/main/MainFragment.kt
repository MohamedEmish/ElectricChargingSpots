package com.amosh.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.amosh.base.BaseFragment
import com.amosh.common.isOffline
import com.amosh.feature.databinding.FragmentMainBinding
import com.amosh.feature.ui.MainViewModel
import com.amosh.feature.ui.contract.MainContract
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

    private val adapter: SpotsAdapter by lazy {
        SpotsAdapter({ spot ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(spot)
            findNavController().navigate(action)
        }, {
            getSpotsList()
        })
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvSpots.adapter = adapter
        initObservers()
        getSpotsList()
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
                            binding.pbProgress.isVisible = true
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
                                Toast.makeText(
                                    requireContext(),
                                    "No internet connection, please try again later",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            }
        }
    }

    private fun getSpotsList() {
        viewModel.setEvent(MainContract.Event.OnFetchSpotsList)
    }
}



