package com.amosh.remote.mapper

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.remote.model.SpotsNetworkResponse
import com.amosh.remote.model.Station
import javax.inject.Inject

class SpotNetworkMapper @Inject constructor() :
    Mapper<Station, ChargerSpotDTO> {
    override fun from(i: Station?): ChargerSpotDTO {
        return ChargerSpotDTO(
            id = i?.id,
            name = i?.name,
            distance = i?.distance,
            contactPhone = i?.contacts?.phone?.get(0)?.value,
            latitude = i?.position?.latitude,
            longitude = i?.position?.longitude,
            address = i?.address?.getAddressString(),
            numberOfConnectors = i?.totalNumberOfConnectors,
            supplierName = i?.connectors?.connector?.get(0)?.supplierName,
            chargeCapacity = i?.connectors?.connector?.get(0)?.chargeCapacity,
            maxPowerLevel = i?.connectors?.connector?.get(0)?.maxPowerLevel,
            customerChargeLevel = i?.connectors?.connector?.get(0)?.customerChargeLevel,
            customerConnectorName = i?.connectors?.connector?.get(0)?.customerConnectorName,
            fixedCable = i?.connectors?.connector?.get(0)?.fixedCable
        )
    }

    override fun to(o: ChargerSpotDTO?): Station {
        return Station()
    }
}