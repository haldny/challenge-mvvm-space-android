package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage

interface FetchLaunchesRepository {
    suspend fun getsLaunches(): LaunchesPage
}