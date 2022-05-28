package com.amosh.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChargerConnectorUiModel(
    val supplierName: String? = null,
    val chargeCapacity: String? = null,
    val maxPowerLevel: Double? = null,
    val customerChargeLevel: String? = null,
    val customerConnectorName: String? = null,
    val fixedCable: Boolean? = null,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChargerConnectorUiModel

        if (supplierName != other.supplierName) return false
        if (chargeCapacity != other.chargeCapacity) return false
        if (maxPowerLevel != other.maxPowerLevel) return false
        if (customerChargeLevel != other.customerChargeLevel) return false
        if (customerConnectorName != other.customerConnectorName) return false
        if (fixedCable != other.fixedCable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = supplierName?.hashCode() ?: 0
        result = 31 * result + (chargeCapacity?.hashCode() ?: 0)
        result = 31 * result + (maxPowerLevel?.hashCode() ?: 0)
        result = 31 * result + (customerChargeLevel?.hashCode() ?: 0)
        result = 31 * result + (customerConnectorName?.hashCode() ?: 0)
        result = 31 * result + (fixedCable?.hashCode() ?: 0)
        return result
    }
}
