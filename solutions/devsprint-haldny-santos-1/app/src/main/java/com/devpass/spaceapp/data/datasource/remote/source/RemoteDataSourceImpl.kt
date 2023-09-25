package com.devpass.spaceapp.data.datasource.remote.source

import com.devpass.spaceapp.data.datasource.remote.api.SpaceXAPIService
import com.devpass.spaceapp.data.datasource.remote.mapper.toLandpadsDetailDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toLaunchesPageDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toRocketDomain
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.domain.model.LandpadsDetail as LandpadsDetailDomain
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain
import com.devpass.spaceapp.domain.model.LaunchesPage as LaunchesPageDomain

class RemoteDataSourceImpl(private val apiService: SpaceXAPIService) : RemoteDataSource {
    override suspend fun getLandpadsDetail(id: String): LandpadsDetailDomain {
        return apiService.getLandpadsDetail(id).toLandpadsDetailDomain()
    }

    override suspend fun getRocketsDetail(id: String): RocketsDetailDomain {
        return apiService.getRocketsDetail(id).toRocketDomain()
    }

    override suspend fun getsLaunches(params: QueryParams): LaunchesPageDomain {
        return apiService.getsLaunches(params).toLaunchesPageDomain()
    }
}
