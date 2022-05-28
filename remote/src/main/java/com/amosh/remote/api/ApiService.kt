package com.amosh.remote.api

import com.amosh.remote.BuildConfig
import com.amosh.remote.model.SpotsNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("poi")
    suspend fun getChargingSpots(
        @Query("compat") compat: Boolean = true,
        @Query("distance") distance: Int = 5000,
        @Query("distanceunit") distanceUnit: String = "km",
        @Query("latitude") latitude: Int? = null,
        @Query("longitude") longitude: Int? = null,
        @Query("maxresults") maxResults: Int? = 20,
        @Query("verbose") verbose: Boolean? = false,
        @Query("key") apiKey: String = BuildConfig.API_KEY,
    ): List<SpotsNetworkResponse>
}