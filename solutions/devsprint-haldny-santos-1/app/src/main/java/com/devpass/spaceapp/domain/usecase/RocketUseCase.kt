package com.devpass.spaceapp.domain.usecase

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain
import com.devpass.spaceapp.data.repository.rocket.RocketRepository
import kotlinx.coroutines.flow.Flow

interface RocketUseCase {
    suspend operator fun invoke(id: String): Flow<ResultData<RocketsDetailDomain>>
}

class RocketUseCaseImpl(private val repository: RocketRepository) : RocketUseCase {
    override suspend fun invoke(id: String): Flow<ResultData<RocketsDetailDomain>> {
        return repository.getRocketsDetail(id)
    }
}
