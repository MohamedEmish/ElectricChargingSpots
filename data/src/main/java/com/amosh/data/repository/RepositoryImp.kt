package com.amosh.data.repository

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.repository.Repository
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val spotMapper: Mapper<ChargerSpotDTO, ChargerSpotEntity>,
) : Repository {

}