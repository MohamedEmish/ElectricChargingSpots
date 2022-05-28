package com.amosh.data.repository

import com.amosh.data.model.ChargerSpotDTO


/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addItem(spot: ChargerSpotDTO): Long

    suspend fun getItems(): List<ChargerSpotDTO>

}