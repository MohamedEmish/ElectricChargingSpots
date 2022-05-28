package com.amosh.remote.model

data class SpotsNetworkResponse(
    val ID: Long? = null,
    val UUID: String? = null,
    val AddressInfo: StationAddress? = null,
    val Connections: List<StationConnector>? = null,
    val NumberOfPoints: Int? = null,
)