package com.devpass.spaceapp.data.datasource.remote.source

import com.devpass.spaceapp.data.datasource.remote.api.SpaceXAPIService
import com.devpass.spaceapp.data.datasource.remote.model.LandpadsDetail
import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val apiService: SpaceXAPIService) : RemoteDataSource {
    override suspend fun getLandpadsDetail(id: String): LandpadsDetail {
        return apiService.getLandpadsDetail(id)
    }

    override suspend fun getRocketsDetail(id: String): RocketsDetail {
        return apiService.getRocketsDetail(id)
    }

    override suspend fun getsLaunches(params: QueryParams): Flow<LaunchesPage> = flow {
        emit(apiService.getsLaunches(params))
    }
}
