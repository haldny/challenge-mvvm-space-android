package com.devpass.spaceapp.data.repository.launches

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.OptionsRequest
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.SortRequest
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val LIMIT = 10
private const val FLIGHT_NUMBER = "asc"

class FetchLaunchesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : FetchLaunchesRepository {
    override suspend fun getsLaunches(): Flow<ResultData<LaunchesPage>> = flow {
        return@flow try {
            val result = remoteDataSource.getsLaunches(
                QueryParams(
                    OptionsRequest(SortRequest(FLIGHT_NUMBER), LIMIT)
                )
            )
            emit(ResultData.Success(result))
        } catch (t: Throwable) {
            emit(ResultData.Error(t))
        }
    }
}