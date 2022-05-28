package com.amosh.electricchargingspots.di

import com.amosh.common.Mapper
import com.amosh.data.mapper.ChargingSpotDataDomainMapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.domain.entity.ChargerSpotEntity
import com.amosh.feature.mapper.ChargingSpotDomainUiMapper
import com.amosh.feature.model.ChargerSpotUiModel
import com.amosh.local.mapper.ChargingSpotLocalDataMapper
import com.amosh.local.model.ChargerSpotLocalModel
import com.amosh.remote.mapper.SpotNetworkMapper
import com.amosh.remote.model.SpotsNetworkResponse
import com.amosh.remote.model.Station
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Locale Mappers
    @Binds
    abstract fun bindsChargingSpotLocalDataMapper(mapper: ChargingSpotLocalDataMapper): Mapper<ChargerSpotLocalModel, ChargerSpotDTO>
    //endregion

    //region Data Mappers
    @Binds
    abstract fun bindsChargingSpotDataDomainMapper(mapper : ChargingSpotDataDomainMapper) : Mapper<ChargerSpotDTO, ChargerSpotEntity>
    //endregion


    //region Presentation Mappers
    @Binds
    abstract fun bindsChargingSpotDomainUiMapper(mapper : ChargingSpotDomainUiMapper) : Mapper<ChargerSpotEntity, ChargerSpotUiModel>
    //endregion

    //region Remote Mappers
    @Binds
    abstract fun bindsSpotNetworkMapper(mapper: SpotNetworkMapper): Mapper<Station, ChargerSpotDTO>
    //endregion

}