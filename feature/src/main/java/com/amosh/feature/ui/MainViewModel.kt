package com.amosh.feature.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.amosh.base.BaseViewModel
import com.amosh.common.Mapper
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.userCase.GetChargerSpotsUseCase
import com.amosh.feature.model.ChargerSpotUiModel
import com.amosh.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getChargerSpotsUseCase: GetChargerSpotsUseCase,
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

    private fun fetchSpotsList(lat: Double, lng: Double) = viewModelScope.launch {
        Log.d("fetchSpotsList", "lat: $lat lng: $lng")
    }
}
