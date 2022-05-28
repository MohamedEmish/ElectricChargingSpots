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
            latitude = i?.latitude,
            longitude = i?.longitude,
            address = i?.address,
            numberOfConnectors = i?.numberOfConnectors,
            maxPowerLevel = i?.maxPowerLevel,
            distance = i?.distance
        )
    }

    override fun to(o: ChargerSpotDTO?): ChargerSpotLocalModel {
        return ChargerSpotLocalModel(
            id = o?.id ?: "",
            name = o?.name,
            latitude = o?.latitude,
            longitude = o?.longitude,
            address = o?.address,
            numberOfConnectors = o?.numberOfConnectors,
            maxPowerLevel = o?.maxPowerLevel,
            distance = o?.distance
        )
    }
}