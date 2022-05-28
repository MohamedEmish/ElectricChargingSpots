package com.amosh.remote.model

data class StationConnector(
    val supplierName: String? = null,
    val connectorType: ConnectorType? = null,
    val chargeCapacity: String? = null,
    val maxPowerLevel: Double? = null,
    val customerChargeLevel: String? = null,
    val customerConnectorName: String? = null,
    val fixedCable: Boolean? = null,


    )