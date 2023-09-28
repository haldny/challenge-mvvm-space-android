package com.devpass.spaceapp.di

import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCase
import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCaseImpl
import com.devpass.spaceapp.domain.usecase.RocketUseCase
import com.devpass.spaceapp.domain.usecase.RocketUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindFetchLaunchesUseCase(
        fetchLaunchesUseCaseImpl: FetchLaunchesUseCaseImpl
    ): FetchLaunchesUseCase

    @Binds
    fun bindRocketUseCase(
        rocketUseCaseImpl: RocketUseCaseImpl
    ): RocketUseCase
}
