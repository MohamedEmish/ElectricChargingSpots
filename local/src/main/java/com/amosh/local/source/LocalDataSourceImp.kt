package com.amosh.local.source

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.data.repository.LocalDataSource
import com.amosh.local.database.ChargerSpotsDao
import com.amosh.local.model.ChargerSpotLocalModel
import javax.inject.Inject


/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val spotsDao: ChargerSpotsDao,
    private val spotMapper: Mapper<ChargerSpotLocalModel, ChargerSpotDTO>
) : LocalDataSource {
    override suspend fun addItem(spot: ChargerSpotDTO): Long {
        val spotLocalModel = spotMapper.to(spot)
        return spotsDao.addSpot(spot = spotLocalModel)
    }

    override suspend fun getItems(): List<ChargerSpotDTO> {
        val spotLocalList = spotsDao.getSpots()
        return spotMapper.fromList(spotLocalList)
    }
}