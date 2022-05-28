package com.amosh.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amosh.local.model.ChargerSpotLocalModel

@Dao
interface ChargerSpotsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpot(spot: ChargerSpotLocalModel): Long

    @Query("SELECT * FROM spots")
    suspend fun getSpots(): List<ChargerSpotLocalModel>

}