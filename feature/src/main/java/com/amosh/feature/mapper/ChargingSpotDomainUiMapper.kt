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
            distance = i?.distance,
            contactPhone = i?.contactPhone,
            latitude = i?.latitude,
            longitude = i?.longitude,
            address = i?.address,
            numberOfConnectors = i?.numberOfConnectors,
            connectors = ChargerConnectorUiModel(
                supplierName = i?.supplierName,
                chargeCapacity = i?.chargeCapacity,
                maxPowerLevel = i?.maxPowerLevel,
                customerChargeLevel = i?.customerChargeLevel,
                customerConnectorName = i?.customerConnectorName,
                fixedCable = i?.fixedCable
            )
        )
    }

    override fun to(o: ChargerSpotUiModel?): ChargerSpotEntity {
        return ChargerSpotEntity(
            id = o?.id,
            name = o?.name,
            distance = o?.distance,
            contactPhone = o?.contactPhone,
            latitude = o?.latitude,
            longitude = o?.longitude,
            address = o?.address,
            numberOfConnectors = o?.numberOfConnectors,
            supplierName = o?.connectors?.supplierName,
            chargeCapacity = o?.connectors?.chargeCapacity,
            maxPowerLevel = o?.connectors?.maxPowerLevel,
            customerChargeLevel = o?.connectors?.customerChargeLevel,
            customerConnectorName = o?.connectors?.customerConnectorName,
            fixedCable = o?.connectors?.fixedCable
        )
    }

}