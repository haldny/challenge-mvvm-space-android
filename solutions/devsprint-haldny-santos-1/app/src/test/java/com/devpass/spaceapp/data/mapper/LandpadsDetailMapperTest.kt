package com.devpass.spaceapp.data.mapper

import com.devpass.spaceapp.data.datasource.remote.mapper.toLandpadsDetailDomain
import com.devpass.spaceapp.data.mockLandpadsDetail
import com.devpass.spaceapp.data.mockLandpadsDetailResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class LandpadsDetailMapperTest {

    @Test
    fun `map LandpadsDetailResponse to LandpadsDetailDomain`() {

        val expected = mockLandpadsDetail
        val actual = mockLandpadsDetailResponse.toLandpadsDetailDomain()

        assertEquals(expected, actual)
    }
}
