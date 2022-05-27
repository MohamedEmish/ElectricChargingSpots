package com.amosh.data.repository

import com.amosh.common.Mapper
import com.amosh.data.model.SpotDTO
import com.amosh.domain.entity.SpotEntity
import com.amosh.domain.repository.Repository
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val movieMapper: Mapper<SpotDTO, SpotEntity>,
) : Repository {

}