package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.ResultData
import kotlinx.coroutines.flow.Flow

interface FetchLaunchesRepository {
    suspend fun getsLaunches(): Flow<ResultData<LaunchesPage>>
}
