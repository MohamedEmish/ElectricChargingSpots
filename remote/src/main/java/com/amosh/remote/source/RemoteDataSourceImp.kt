package com.amosh.remote.source

import com.amosh.common.Constants
import com.amosh.common.Mapper
import com.amosh.common.Resource
import com.amosh.common.distance
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.data.repository.RemoteDataSource
import com.amosh.remote.api.ApiService
import com.amosh.remote.mapper.SpotNetworkMapper
import com.amosh.remote.model.SpotsNetworkResponse
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val spotMapper: Mapper<SpotsNetworkResponse, ChargerSpotDTO>,
) : RemoteDataSource {
    override suspend fun getChargingSpots(
        lat: Double,
        lng: Double,
    ): Resource<List<ChargerSpotDTO>> {
        val networkData = apiService.getChargingSpots(
            latitude = lat.toInt(),
            longitude = lng.toInt(),
        )
//        return when (networkData.error) {
//            null -> {
                val list: MutableList<ChargerSpotDTO> = mutableListOf()
                networkData.forEach {
                    it.AddressInfo?.Distance = distance(lat, lng, it.AddressInfo?.Latitude ?: Constants.DEFAULT_LAT, it.AddressInfo?.Longitude ?: Constants.DEFAULT_LNG)
                    list.add(spotMapper.from(it))
                }
                return Resource.Success(list)
//            }
//            else -> {
//                Resource.Error(Exception(networkData.error_description))
//            }
//        }
    }
}