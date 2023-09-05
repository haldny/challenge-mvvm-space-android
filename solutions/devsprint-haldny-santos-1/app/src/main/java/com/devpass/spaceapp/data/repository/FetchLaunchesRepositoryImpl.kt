package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.OptionsRequest
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.SortRequest
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.ResultData

private const val LIMIT = 10
private const val FLIGHT_NUMBER = "asc"

class FetchLaunchesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FetchLaunchesRepository {
    override suspend fun getsLaunches(): ResultData<LaunchesPage> {
        return try {
            ResultData.Success(
                remoteDataSource.getsLaunches(
                    QueryParams(OptionsRequest(SortRequest(FLIGHT_NUMBER), LIMIT))
                )
            )
        } catch (e: Exception) {
            ResultData.Error(exception = e)
        }
    }
}
