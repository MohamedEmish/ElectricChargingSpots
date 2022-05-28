package com.amosh.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "spots")
data class ChargerSpotLocalModel(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val distance: Double? = null,
    val contactPhone: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val address: String? = null,
    val numberOfConnectors: Int? = null,
    // connector data
    val supplierName: String? = null,
    val chargeCapacity: String? = null,
    val maxPowerLevel: Double? = null,
    val customerChargeLevel: String? = null,
    val customerConnectorName: String? = null,
    val fixedCable: Boolean? = null,
)
