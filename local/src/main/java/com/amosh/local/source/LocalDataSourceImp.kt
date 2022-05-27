package com.amosh.local.source

import com.amosh.common.Mapper
import com.amosh.data.model.SpotDTO
import com.amosh.data.repository.LocalDataSource
import com.amosh.local.database.SpotsDao
import com.amosh.local.model.SpotLocalModel
import javax.inject.Inject


/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val spotsDao: SpotsDao,
    private val movieMapper: Mapper<SpotLocalModel, SpotDTO>
) : LocalDataSource {


}