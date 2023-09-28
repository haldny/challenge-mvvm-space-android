package com.devpass.spaceapp.domain.usecase

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.model.LaunchesPage as LaunchesPageDomain
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchLaunchesUseCase {
    suspend operator fun invoke(): Flow<ResultData<LaunchesPageDomain>>
}

class FetchLaunchesUseCaseImpl @Inject constructor(
    private val repository: FetchLaunchesRepository
) : FetchLaunchesUseCase {

    override suspend fun invoke(): Flow<ResultData<LaunchesPageDomain>> {
        return repository.getsLaunches()
    }
}
