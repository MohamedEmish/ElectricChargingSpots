package com.amosh.remote.source

import com.amosh.data.repository.RemoteDataSource
import com.amosh.remote.api.ApiService
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
//    private val movieMapper: Mapper<NetworkResponse, DTO>,
) : RemoteDataSource {

}