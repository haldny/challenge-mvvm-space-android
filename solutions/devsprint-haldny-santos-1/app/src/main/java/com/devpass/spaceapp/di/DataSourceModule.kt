package com.devpass.spaceapp.di

import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}
