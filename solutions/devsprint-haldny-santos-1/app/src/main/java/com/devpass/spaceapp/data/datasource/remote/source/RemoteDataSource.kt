package com.devpass.spaceapp.data.datasource.remote.source

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage as LaunchesPageData
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import kotlinx.coroutines.flow.Flow
import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail as RocketsDetailData
import com.devpass.spaceapp.data.datasource.remote.model.LandpadsDetail as LandpadsDetailData

interface RemoteDataSource {
    suspend fun getLandpadsDetail(id: String): LandpadsDetailData
    suspend fun getRocketsDetail(id: String): RocketsDetailData
    suspend fun getsLaunches(params: QueryParams): Flow<LaunchesPageData>
}
