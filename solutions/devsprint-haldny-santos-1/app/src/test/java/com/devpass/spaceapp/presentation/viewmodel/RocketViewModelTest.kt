package com.devpass.spaceapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.mockIdParam
import com.devpass.spaceapp.data.mockRocketModel
import com.devpass.spaceapp.data.mockRocketsDetail
import com.devpass.spaceapp.domain.usecase.RocketUseCase
import com.devpass.spaceapp.presentation.rocket.RocketUiState
import com.devpass.spaceapp.presentation.rocket.RocketViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private val usecase = mockk<RocketUseCase>(relaxed = true)
    private lateinit var viewModel: RocketViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = RocketViewModel(usecase)
    }

    @Test
    fun `when usecase is invoke should set RocketUiState Success`() {

        val mockParam = mockIdParam
        val mockRocketModel = mockRocketModel

        val mockRocketsDetail = mockRocketsDetail
        val mockResultRocket = ResultData.Success(mockRocketsDetail)
        val expected = RocketUiState.Success(mockRocketModel)

        coEvery { usecase.invoke(any()) } returns flowOf(mockResultRocket)

        runTest { viewModel.getRocket(mockParam) }

        coVerify { usecase.invoke(mockParam) }
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `when usecase is invoke should set RocketUiState Error`() {

        val mockResultRocket = ResultData.Error(Throwable("Error"))
        val expected = RocketUiState.Error(Throwable("Error"))

        coEvery { usecase.invoke(any()) } returns flowOf(mockResultRocket)

        runTest { viewModel.getRocket(mockIdParam) }

        coVerify { usecase.invoke(mockIdParam) }
        assertEquals(expected, viewModel.uiState.value)
    }
}
