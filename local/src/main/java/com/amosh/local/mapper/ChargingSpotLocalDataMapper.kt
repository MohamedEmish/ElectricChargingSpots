package com.amosh.local.mapper

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.local.model.ChargerSpotLocalModel
import javax.inject.Inject

class ChargingSpotLocalDataMapper @Inject constructor() : Mapper<ChargerSpotLocalModel, ChargerSpotDTO> {
    override fun from(i: ChargerSpotLocalModel?): ChargerSpotDTO {
        return ChargerSpotDTO(
            id = i?.id,
            name = i?.name,
            distance = i?.distance,
            contactPhone = i?.contactPhone,
            latitude = i?.latitude,
            longitude = i?.longitude,
            address = i?.address,
            numberOfConnectors = i?.numberOfConnectors,
            supplierName = i?.supplierName,
            chargeCapacity = i?.chargeCapacity,
            maxPowerLevel = i?.maxPowerLevel,
            customerChargeLevel = i?.customerChargeLevel,
            customerConnectorName = i?.customerConnectorName,
            fixedCable = i?.fixedCable
        )
    }

    override fun to(o: ChargerSpotDTO?): ChargerSpotLocalModel {
        return ChargerSpotLocalModel(
            id = o?.id ?: "",
            name = o?.name,
            distance = o?.distance,
            contactPhone = o?.contactPhone,
            latitude = o?.latitude,
            longitude = o?.longitude,
            address = o?.address,
            numberOfConnectors = o?.numberOfConnectors,
            supplierName = o?.supplierName,
            chargeCapacity = o?.chargeCapacity,
            maxPowerLevel = o?.maxPowerLevel,
            customerChargeLevel = o?.customerChargeLevel,
            customerConnectorName = o?.customerConnectorName,
            fixedCable = o?.fixedCable
        )
    }
}