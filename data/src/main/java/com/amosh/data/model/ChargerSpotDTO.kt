package com.amosh.data.model

data class ChargerSpotDTO(
    val id: String? = null,
    val name: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val address: String? = null,
    val numberOfConnectors: Int? = null,
    // connector data
    val maxPowerLevel: Double? = null,
    val distance: Double? = null,
)
