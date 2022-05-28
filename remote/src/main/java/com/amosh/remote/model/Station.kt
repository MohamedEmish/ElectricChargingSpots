package com.amosh.remote.model

data class Station(
    val poolId: String? = null,
    val totalNumberOfConnectors: Int? = null,
    val distance: Double? = null,
    val name: String? = null,
    val id: String? = null,
    val position: LatLngPosition? = null,
    val contacts: StationContact? = null,
    val address: StationAddress? = null,
    val connectors: StationConnectors? = null,
)