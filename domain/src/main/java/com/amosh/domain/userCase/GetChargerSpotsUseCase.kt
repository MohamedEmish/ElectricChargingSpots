package com.amosh.domain.userCase

import com.amosh.common.Constants
import com.amosh.common.Resource
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.qualifiers.IoDispatcher
import com.amosh.domain.repository.Repository
import com.amosh.domain.userCase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetChargerSpotsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseUseCase<List<ChargerSpotEntity>, Map<String, Double>>() {
    override suspend fun buildDetailsRequest(
        params: Map<String, Double>?,
    ): Flow<Resource<List<ChargerSpotEntity>>> {
        return repository.getChargingSpots(
            lat = params?.getOrDefault(Constants.LATITUDE, Constants.DEFAULT_LAT) ?: Constants.DEFAULT_LAT,
            lng = params?.getOrDefault(Constants.LONGITUDE, Constants.DEFAULT_LNG) ?: Constants.DEFAULT_LAT
        ).flowOn(dispatcher)
    }
}