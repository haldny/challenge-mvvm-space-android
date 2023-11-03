package com.devpass.spaceapp.data.repository

import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.data.datasource.remote.source.RemoteDataSource
import com.devpass.spaceapp.data.mockIdParam
import com.devpass.spaceapp.data.mockRocketsDetail
import com.devpass.spaceapp.data.repository.rocket.RocketRepository
import com.devpass.spaceapp.data.repository.rocket.RocketRepositoryImpl
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
class RocketRepositoryTest {

    private val remoteDataSource = mockk<RemoteDataSource>(relaxed = true)
    private lateinit var repository: RocketRepository

    @Before
    fun setup() {
        repository = RocketRepositoryImpl(remoteDataSource)
    }

    fun tearDown() {
        confirmVerified(remoteDataSource)
        unmockkAll()
        clearAllMocks()
    }


    @Test
    fun `should return ResultData Success when get Rockets Detail requests call`() = runTest {

        val mockParam = mockIdParam
        val mockRocketsDetail = mockRocketsDetail
        val expected = ResultData.Success(mockRocketsDetail)

        coEvery { remoteDataSource.getRocketsDetail(any()) } returns mockRocketsDetail

       val actual = repository.getRocketsDetail(mockParam).single()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            remoteDataSource.getRocketsDetail(mockParam)
        }
    }

    @Test
    fun `should return ResultData Error when get Rockets Detail requests call error`() = runTest {

        val mockParam = mockIdParam
        val throwable = Throwable("Error")
        val expected = ResultData.Error(throwable)

        coEvery { remoteDataSource.getRocketsDetail(any()) } throws throwable

        val actual = repository.getRocketsDetail(mockParam).single()

        Assert.assertEquals(expected, actual)

        coVerify(exactly = 1) {
            remoteDataSource.getRocketsDetail(mockParam)
        }
    }
}
