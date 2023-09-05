package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.ResultData

interface FetchLaunchesRepository {
    suspend fun getsLaunches(): ResultData<LaunchesPage>
}
