package com.amosh.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChargerSpotUiModel(
    val id: String? = null,
    val name: String? = null,
    val distance: Double? = null,
    val contactPhone: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val address: String? = null,
    val numberOfConnectors: Int? = null,
    val connectors: List<ChargerConnectorUiModel>,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChargerSpotUiModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (distance != other.distance) return false
        if (contactPhone != other.contactPhone) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (address != other.address) return false
        if (numberOfConnectors != other.numberOfConnectors) return false
        if (connectors != other.connectors) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (distance?.hashCode() ?: 0)
        result = 31 * result + (contactPhone?.hashCode() ?: 0)
        result = 31 * result + (latitude?.hashCode() ?: 0)
        result = 31 * result + (longitude?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (numberOfConnectors ?: 0)
        result = 31 * result + connectors.hashCode()
        return result
    }
}
