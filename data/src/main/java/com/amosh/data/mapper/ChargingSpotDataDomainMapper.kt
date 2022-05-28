package com.amosh.data.mapper

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.domain.entity.ChargerSpotEntity
import javax.inject.Inject

class ChargingSpotDataDomainMapper @Inject constructor() : Mapper<ChargerSpotDTO, ChargerSpotEntity> {
    override fun from(i: ChargerSpotDTO?): ChargerSpotEntity {
        return ChargerSpotEntity(
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

    override fun to(o: ChargerSpotEntity?): ChargerSpotDTO {
        return ChargerSpotDTO(
            id = o?.id,
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