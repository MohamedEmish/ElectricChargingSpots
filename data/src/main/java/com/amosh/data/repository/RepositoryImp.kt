package com.amosh.data.repository

import android.util.Log
import com.amosh.common.Mapper
import com.amosh.common.Resource
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val spotMapper: Mapper<ChargerSpotDTO, ChargerSpotEntity>,
) : Repository {
    override suspend fun getChargingSpots(
        lat: Double, lng: Double,
    ): Flow<Resource<List<ChargerSpotEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource

                // Emit data
                val resultList: MutableList<ChargerSpotEntity> = mutableListOf()

                emit(Resource.Success(resultList))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
                // If remote request fails
                try {
                    // Get data from LocalDataSource

                    // Emit data
                    val resultList: MutableList<ChargerSpotEntity> = mutableListOf()


                    emit(Resource.Success(resultList))
                } catch (ex: Exception) {
                    // Emit error
                    emit(Resource.Error(ex))
                }
            }
        }

    }
}