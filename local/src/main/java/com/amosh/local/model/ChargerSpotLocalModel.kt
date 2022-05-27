package com.amosh.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spots")
data class ChargerSpotLocalModel(
    @PrimaryKey
    val id: Int
)
