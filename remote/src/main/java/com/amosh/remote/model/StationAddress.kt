package com.amosh.remote.model

data class StationAddress(
    val ID: Long? = null,
    val Title: String? = null,
    val AddressLine1: String? = null,
    val Town: String? = null,
    val StateOrProvince: String? = null,
    val CountryID: Int? = null,
    val Latitude: Double? = null,
    val Longitude: Double? = null,
    var Distance: Double? = null,
) {
    fun getAddressString(): String = "$AddressLine1, $Town"
}