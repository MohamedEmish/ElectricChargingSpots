package com.amosh.remote.model

data class SpotsNetworkResponse(
    val hasMore: Boolean? = null,
    val count: Int? = null,
    val evStations: EvStations? = null,
)