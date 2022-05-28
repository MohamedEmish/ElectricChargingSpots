package com.amosh.data.repository

import com.amosh.common.Resource
import com.amosh.data.model.ChargerSpotDTO


/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {
    suspend fun getChargingSpots(lat: Double, lng: Double): Resource<List<ChargerSpotDTO>>
}