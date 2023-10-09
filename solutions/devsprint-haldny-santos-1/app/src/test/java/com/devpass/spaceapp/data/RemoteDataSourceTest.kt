package com.devpass.spaceapp.data

import com.devpass.spaceapp.data.datasource.remote.api.SpaceXAPIService
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSourceImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private val apiService = mockk<SpaceXAPIService>(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    fun tearDown() {
        confirmVerified(apiService)
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun `should return Launches Page when gets Launches requests call`() = runTest {

        val mockQueryParams = mockQueryParams
        val mockResponse = mockLaunchesPageResponse
        val expected = mockLaunchesPage

        coEvery { apiService.getsLaunches(any()) } returns mockResponse

        val actual = remoteDataSource.getsLaunches(mockQueryParams)

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            apiService.getsLaunches(mockQueryParams)
        }
    }

    @Test
    fun `should return Rockets Detail when get Rockets Detail requests call`() = runTest {

        val mockParam = mockIdParam
        val response = mockRocketsDetailResponse
        val expected = mockRocketsDetail

        coEvery { apiService.getRocketsDetail(mockParam) } returns response

        val actual = remoteDataSource.getRocketsDetail(mockParam)

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            apiService.getRocketsDetail(mockParam)
        }
    }

    @Test
    fun `should return Landpads Detail when get Landpads Detail requests call`() = runTest {

        val mockParam = mockIdParam
        val mockLandpadsDetailResponse = mockLandpadsDetailResponse
        val expected = mockLandpadsDetail

        coEvery {
            apiService.getLandpadsDetail(mockParam)
        } returns mockLandpadsDetailResponse

        val actual = remoteDataSource.getLandpadsDetail(mockParam)

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            apiService.getLandpadsDetail(mockParam)
        }
    }
}
