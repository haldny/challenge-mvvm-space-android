package com.devpass.spaceapp.data.mapper

import com.devpass.spaceapp.data.datasource.remote.mapper.toRocketDomain
import com.devpass.spaceapp.data.mockRocketsDetail
import com.devpass.spaceapp.data.mockRocketsDetailResponse
import org.junit.Assert
import org.junit.Test

class RocketDataMapperTest {

    @Test
    fun `map RocketsDetailResponse to RocketsDetailDomain`() {

        val expected = mockRocketsDetail
        val actual = mockRocketsDetailResponse.toRocketDomain()

        Assert.assertEquals(expected, actual)
    }
}
