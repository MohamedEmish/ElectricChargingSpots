package com.amosh.remote.source

import com.amosh.common.Mapper
import com.amosh.data.model.ChargerSpotDTO
import com.amosh.data.repository.RemoteDataSource
import com.amosh.remote.api.ApiService
import com.amosh.remote.model.SpotsNetworkResponse
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val spotMapper: Mapper<SpotsNetworkResponse, ChargerSpotDTO>,
) : RemoteDataSource {

}