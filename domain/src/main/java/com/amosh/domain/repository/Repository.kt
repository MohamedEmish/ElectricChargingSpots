package com.amosh.domain.repository

import com.amosh.common.Resource
import com.amosh.domain.entity.ChargerSpotEntity
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {
    suspend fun getChargingSpots(lat: Double, lng: Double): Flow<Resource<List<ChargerSpotEntity>>>
    suspend fun getLocalChargingSpots(): Flow<Resource<List<ChargerSpotEntity>>>
}