package com.devpass.spaceapp.domain.usecase

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain
import com.devpass.spaceapp.data.repository.rocket.RocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RocketUseCase {
    suspend operator fun invoke(id: String): Flow<ResultData<RocketsDetailDomain>>
}

class RocketUseCaseImpl @Inject constructor(
    private val repository: RocketRepository
) : RocketUseCase {

    override suspend fun invoke(id: String): Flow<ResultData<RocketsDetailDomain>> {
        return repository.getRocketsDetail(id)
    }
}
