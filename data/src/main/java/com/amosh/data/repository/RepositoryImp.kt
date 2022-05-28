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
import kotlin.math.ln

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
                val data = remoteDataSource.getChargingSpots(
                    lat = lat,
                    lng = lng
                )
                // Emit data
                when (data) {
                    is Resource.Error -> {
                        emit(Resource.Error(data.exception))
                    }
                    is Resource.Success -> {
                        val resultList: MutableList<ChargerSpotEntity> = mutableListOf()
                        data.data.forEach {
                            resultList.add(spotMapper.from(it))
                            localDataSource.addItem(it)
                        }
                        emit(Resource.Success(resultList))
                    }
                    else -> emit(Resource.Empty)
                }
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getItems()
                    // Emit data
                    val resultList: MutableList<ChargerSpotEntity> = mutableListOf()
                    local.forEach {
                        resultList.add(spotMapper.from(it))
                    }
                    emit(Resource.Success(resultList))
                } catch (ex: Exception) {
                    // Emit error
                    emit(Resource.Error(ex))
                }
            }
        }

    }

    override suspend fun getLocalChargingSpots(): Flow<Resource<List<ChargerSpotEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = localDataSource.getItems()
                if (data.isEmpty()) {
                    emit(Resource.Empty)
                } else {
                    val resultList: MutableList<ChargerSpotEntity> = mutableListOf()
                    data.forEach {
                        resultList.add(spotMapper.from(it))
                        localDataSource.addItem(it)
                    }
                    emit(Resource.Success(resultList))
                }
                // Emit data
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
        }
    }
}