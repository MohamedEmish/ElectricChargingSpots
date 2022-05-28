package com.amosh.feature.ui.contract

import com.amosh.base.UiEffect
import com.amosh.base.UiEvent
import com.amosh.base.UiState
import com.amosh.feature.model.ChargerSpotUiModel

/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        data class OnFetchSpotsList(val lat: Double, val lng: Double) : Event()
    }

    data class State(
        val spotsState: SpotState,
        val selectedSpot: ChargerSpotUiModel? = null
    ) : UiState

    sealed class SpotState {
        object Idle : SpotState()
        object Loading : SpotState()
        data class Success(val spotList: List<ChargerSpotUiModel>?) : SpotState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}