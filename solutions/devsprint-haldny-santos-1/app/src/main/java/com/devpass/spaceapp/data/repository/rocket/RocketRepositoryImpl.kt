package com.devpass.spaceapp.data.repository.rocket

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDetailDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : RocketRepository {
    override suspend fun getRocketsDetail(id: String): Flow<ResultData<RocketsDetailDomain>> = flow {
        return@flow try {
            val result = remoteDataSource.getRocketsDetail(id)
            emit(ResultData.Success(result))
        } catch (t: Throwable) {
            emit(ResultData.Error(t))
        }
    }
}
