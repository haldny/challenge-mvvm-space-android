package com.devpass.spaceapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.mockLaunchModel
import com.devpass.spaceapp.data.mockLaunchesPage
import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCase
import com.devpass.spaceapp.presentation.launchList.LaunchListUiState
import com.devpass.spaceapp.presentation.launchList.LaunchListViewModel
import com.devpass.spaceapp.presentation.rocket.RocketUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private val usecase = mockk<FetchLaunchesUseCase>(relaxed = true)
    private lateinit var viewModel: LaunchListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = LaunchListViewModel(usecase)
    }

    @Test
    fun `when usecase is invoke should set LaunchListUiState Success`() {

        val mockLaunchesPage = mockLaunchesPage
        val mockResultLaunchesPage = ResultData.Success(mockLaunchesPage)

        val mockLaunchModel = listOf(mockLaunchModel)
        val expected = LaunchListUiState.Success(mockLaunchModel)

        coEvery { usecase.invoke() } returns flowOf(mockResultLaunchesPage)

        runTest { viewModel.getLaunches() }

        coVerify { usecase.invoke() }
        Assert.assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `when usecase is invoke should set LaunchListUiState Error`() {

        val throwable = Throwable("Error")
        val mockResultLaunchesPage = ResultData.Error(throwable)
        val expected = LaunchListUiState.Error(throwable)

        coEvery { usecase.invoke() } returns flowOf(mockResultLaunchesPage)

        runTest { viewModel.getLaunches() }

        coVerify { usecase.invoke() }
        Assert.assertEquals(expected, viewModel.uiState.value)
    }
}
