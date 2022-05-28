package com.amosh.feature.mapper

import com.amosh.common.Mapper
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.feature.model.ChargerConnectorUiModel
import com.amosh.feature.model.ChargerSpotUiModel
import javax.inject.Inject

class ChargingSpotDomainUiMapper @Inject constructor() : Mapper<ChargerSpotEntity, ChargerSpotUiModel> {
    override fun from(i: ChargerSpotEntity?): ChargerSpotUiModel {
        return ChargerSpotUiModel(
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

    override fun to(o: ChargerSpotUiModel?): ChargerSpotEntity {
        return ChargerSpotEntity(
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