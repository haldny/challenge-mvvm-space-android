package com.devpass.spaceapp.di

import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.data.repository.rocket.RocketRepository
import com.devpass.spaceapp.data.repository.rocket.RocketRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindFetchLaunchesRepository(
        fetchLaunchesRepositoryImpl: FetchLaunchesRepositoryImpl
    ): FetchLaunchesRepository

    @Singleton
    @Binds
    fun bindRocketRepositoryImpl(
        rocketRepositoryImpl: RocketRepositoryImpl
    ): RocketRepository
}
