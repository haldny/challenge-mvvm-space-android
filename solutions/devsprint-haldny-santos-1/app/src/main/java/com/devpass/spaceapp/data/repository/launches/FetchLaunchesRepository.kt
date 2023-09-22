package com.devpass.spaceapp.data.repository.launches

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.model.LaunchesPage as LaunchesPageDomain
import kotlinx.coroutines.flow.Flow

interface FetchLaunchesRepository {
    suspend fun getsLaunches(): Flow<ResultData<LaunchesPageDomain>>
}
