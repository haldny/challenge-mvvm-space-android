package com.devpass.spaceapp.domain.usecase

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.mockIdParam
import com.devpass.spaceapp.data.mockLaunchesPage
import com.devpass.spaceapp.data.mockRocketsDetail
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.rocket.RocketRepository
import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCase
import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCaseImpl
import com.devpass.spaceapp.domain.usecase.RocketUseCase
import com.devpass.spaceapp.domain.usecase.RocketUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketUseCaseTest {

    private val repository = mockk<RocketRepository>(relaxed = true)
    private lateinit var usecase: RocketUseCase

    @Before
    fun setup() {
        usecase = RocketUseCaseImpl(repository)
    }

    fun tearDown() {
        confirmVerified(repository)
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `should return ResultData Success when use case is invoke`() = runTest {

        val mockParam = mockIdParam
        val mockRocketsDetail = mockRocketsDetail
        val expected = ResultData.Success(mockRocketsDetail)

        coEvery { repository.getRocketsDetail(any()) } returns flowOf(expected)

        val actual = usecase.invoke(mockIdParam).first()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            repository.getRocketsDetail(mockParam)
        }
    }
}