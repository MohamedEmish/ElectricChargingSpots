package com.amosh.domain.userCase

import com.amosh.common.Resource
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.qualifiers.IoDispatcher
import com.amosh.domain.repository.Repository
import com.amosh.domain.userCase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalChargerSpotsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseUseCase<List<ChargerSpotEntity>, Unit>() {
    override suspend fun buildDetailsRequest(
        params: Unit?,
    ): Flow<Resource<List<ChargerSpotEntity>>> {
        return repository.getLocalChargingSpots()
            .flowOn(dispatcher)
    }
}