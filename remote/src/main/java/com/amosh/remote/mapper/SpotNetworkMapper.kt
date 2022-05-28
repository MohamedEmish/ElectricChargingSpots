package com.amosh.remote.mapper

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.remote.model.SpotsNetworkResponse
import javax.inject.Inject

class SpotNetworkMapper @Inject constructor() :
    Mapper<SpotsNetworkResponse, ChargerSpotDTO> {
    override fun from(i: SpotsNetworkResponse?): ChargerSpotDTO {
        return ChargerSpotDTO(
            id = i?.UUID,
            name = i?.AddressInfo?.Title,
            latitude = i?.AddressInfo?.Latitude,
            longitude = i?.AddressInfo?.Longitude,
            address = i?.AddressInfo?.getAddressString(),
            numberOfConnectors = i?.NumberOfPoints,
            maxPowerLevel = i?.Connections?.get(0)?.PowerKW,
            distance = i?.AddressInfo?.Distance
        )
    }

    override fun to(o: ChargerSpotDTO?): SpotsNetworkResponse {
        return SpotsNetworkResponse()
    }
}