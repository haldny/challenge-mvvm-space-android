package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.OptionsRequest
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.SortRequest
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource

class FetchLaunchesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FetchLaunchesRepository {
    override suspend fun getsLaunches(): LaunchesPage {
        return remoteDataSource.getsLaunches(
            QueryParams(OptionsRequest(SortRequest("asc"), 10))
        )
    }
}