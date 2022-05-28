package com.amosh.feature.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.amosh.base.BaseViewModel
import com.amosh.common.Constants
import com.amosh.common.Mapper
import com.amosh.common.Resource
import com.amosh.common.addIfNotExist
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.userCase.GetChargerSpotsUseCase
import com.amosh.domain.userCase.GetLocalChargerSpotsUseCase
import com.amosh.feature.model.ChargerSpotUiModel
import com.amosh.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getChargerSpotsUseCase: GetChargerSpotsUseCase,
    private val getLocalChargerSpotsUseCase: GetLocalChargerSpotsUseCase,
    private val spotsMapper: Mapper<ChargerSpotEntity, ChargerSpotUiModel>,
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    private val listOfSpots: MutableList<ChargerSpotUiModel> = mutableListOf()
    var currentPage = 1

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            spotsState = MainContract.SpotState.Idle,
            selectedSpot = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchSpotsList -> fetchSpotsList(event.lat, event.lng)
        }
    }

    init {
        getStoredData()
    }

    private fun getStoredData() = viewModelScope.launch {
        getLocalChargerSpotsUseCase.execute(Unit)
            .collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data.isNotEmpty()) {
                            val spotsList = spotsMapper.fromList(it.data)
                            currentPage += 1
                            spotsList.forEach { spot ->
                                listOfSpots.addIfNotExist(spot)
                            }

                            setState {
                                copy(
                                    spotsState = MainContract.SpotState.Success(
                                        spotList = listOfSpots
                                    )
                                )
                            }
                        }
                    }
                    else -> Unit
                }
            }
    }

    private fun fetchSpotsList(lat: Double, lng: Double) = viewModelScope.launch {
        getChargerSpotsUseCase.execute(
            mapOf(
                Constants.LATITUDE to lat,
                Constants.LONGITUDE to lng
            )
        )
            .onStart { emit(Resource.Loading) }
            .collect {
                when (it) {
                    is Resource.Empty -> {
                        setState { copy(spotsState = MainContract.SpotState.Idle) }
                    }
                    is Resource.Error -> {
                        setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                    }
                    is Resource.Loading -> {
                        setState { copy(spotsState = MainContract.SpotState.Loading) }
                    }
                    is Resource.Success -> {
                        val spotsList = spotsMapper.fromList(it.data)
                        currentPage += 1
                        spotsList.forEach { spot ->
                            listOfSpots.addIfNotExist(spot)
                        }

                        setState {
                            copy(
                                spotsState = MainContract.SpotState.Success(
                                    spotList = listOfSpots
                                )
                            )
                        }
                    }
                }
            }
    }
}
