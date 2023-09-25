package com.devpass.spaceapp.data.repository.rocket

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    suspend fun getRocketsDetail(id: String): Flow<ResultData<RocketsDetailDomain>>
}
