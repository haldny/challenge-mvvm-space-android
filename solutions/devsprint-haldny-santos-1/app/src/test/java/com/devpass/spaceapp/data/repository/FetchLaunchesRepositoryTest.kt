package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.mockLaunchesPage
import com.devpass.spaceapp.data.mockQueryParams
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.data.repository.launches.FetchLaunchesRepositoryImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchLaunchesRepositoryTest {

    private val remoteDataSource = mockk<RemoteDataSource>(relaxed = true)
    private lateinit var repository: FetchLaunchesRepository

    @Before
    fun setup() {
        repository = FetchLaunchesRepositoryImpl(remoteDataSource)
    }

    fun tearDown() {
        confirmVerified(remoteDataSource)
        unmockkAll()
        clearAllMocks()
    }


    @Test
    fun `should return ResultData Success when get Launches requests call`() = runTest {

        val mockQueryParams = mockQueryParams
        val mockLaunchesPage = mockLaunchesPage
        val expected = ResultData.Success(mockLaunchesPage)

        coEvery { remoteDataSource.getsLaunches(any()) } returns mockLaunchesPage

        val actual = repository.getsLaunches().single()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            remoteDataSource.getsLaunches(mockQueryParams)
        }
    }

    @Test
    fun `should return ResultData Error when get Launches requests call error`() = runTest {

        val mockQueryParams = mockQueryParams
        val throwable = Throwable("Error")
        val expected = ResultData.Error(throwable)

        coEvery { remoteDataSource.getsLaunches(any()) } throws throwable

        val actual = repository.getsLaunches().single()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            remoteDataSource.getsLaunches(mockQueryParams)
        }
    }
}
