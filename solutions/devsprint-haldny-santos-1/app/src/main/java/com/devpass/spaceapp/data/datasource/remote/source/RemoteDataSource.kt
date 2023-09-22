package com.devpass.spaceapp.data.datasource.remote.source

import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.domain.model.LandpadsDetail as LandpadsDetailDomain
import com.devpass.spaceapp.domain.model.LaunchesPage as LaunchesPageDomain
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain

interface RemoteDataSource {
    suspend fun getLandpadsDetail(id: String): LandpadsDetailDomain
    suspend fun getRocketsDetail(id: String): RocketsDetailDomain
    suspend fun getsLaunches(params: QueryParams): LaunchesPageDomain
}
