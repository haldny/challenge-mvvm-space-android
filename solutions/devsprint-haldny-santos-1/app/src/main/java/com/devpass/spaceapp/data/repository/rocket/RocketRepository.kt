package com.devpass.spaceapp.data.repository.rocket


import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail
import kotlinx.coroutines.flow.Flow

interface RocketRepository {
    suspend fun getRocketsDetail(id: String): Flow<ResultData<RocketsDetail>>
}
