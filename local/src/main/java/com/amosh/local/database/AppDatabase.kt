package com.amosh.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amosh.local.model.ChargerSpotLocalModel

@Database(
    entities = [ChargerSpotLocalModel::class],
    version = 1,
    exportSchema = false
) // We need migration if increase version
abstract class AppDatabase : RoomDatabase() {

    abstract fun spotsDao(): ChargerSpotsDao
}