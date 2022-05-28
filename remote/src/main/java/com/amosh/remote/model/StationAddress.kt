package com.amosh.remote.model

data class StationAddress(
    val city: String? = null,
    val country: String? = null,
    val region: String? = null,
    val street: String? = null,
    val streetNumber: String? = null,
    val postalCode: String? = null,
) {
    fun getAddressString(): String = "$streetNumber $street $region $city"
}