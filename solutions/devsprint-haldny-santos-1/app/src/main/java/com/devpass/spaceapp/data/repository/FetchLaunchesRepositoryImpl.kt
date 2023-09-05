package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.OptionsRequest
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.SortRequest
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val LIMIT = 10
private const val FLIGHT_NUMBER = "asc"

class FetchLaunchesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FetchLaunchesRepository {
    override suspend fun getsLaunches(): Flow<ResultData<LaunchesPage>> {
        return remoteDataSource.getsLaunches(
            QueryParams(
                OptionsRequest(SortRequest(FLIGHT_NUMBER), LIMIT)
            )
        ).map {
            ResultData.Success(it)
        }.catch {
            ResultData.Error(it)
        }
    }
}
