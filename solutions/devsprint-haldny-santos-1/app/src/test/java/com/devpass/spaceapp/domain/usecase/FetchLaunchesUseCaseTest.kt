package com.devpass.spaceapp.domain.usecase

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.mockLaunchesPage
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepository
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
class FetchLaunchesUseCaseTest {

    private val repository = mockk<FetchLaunchesRepository>(relaxed = true)
    private lateinit var usecase: FetchLaunchesUseCase

    @Before
    fun setup() {
        usecase = FetchLaunchesUseCaseImpl(repository)
    }

    fun tearDown() {
        confirmVerified(repository)
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `should return ResultData Success when use case is invoke`() = runTest {

        val mockLaunchesPage = mockLaunchesPage
        val expected = ResultData.Success(mockLaunchesPage)

        coEvery { repository.getsLaunches() } returns flowOf(expected)

        val actual = usecase.invoke().first()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            repository.getsLaunches()
        }
    }
}
