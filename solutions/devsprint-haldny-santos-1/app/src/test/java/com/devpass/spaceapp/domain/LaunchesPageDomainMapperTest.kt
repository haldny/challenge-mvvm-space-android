package com.devpass.spaceapp.domain

import com.devpass.spaceapp.data.datasource.remote.mapper.toLaunchDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toLinkDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toPatchDomain
import com.devpass.spaceapp.data.mockLaunch
import com.devpass.spaceapp.data.mockLaunchModel
import com.devpass.spaceapp.data.mockLaunchResponse
import com.devpass.spaceapp.data.mockLink
import com.devpass.spaceapp.data.mockLinkResponse
import com.devpass.spaceapp.data.mockPatch
import com.devpass.spaceapp.data.mockPatchResponse
import com.devpass.spaceapp.domain.mapper.toLaunchPresentation
import org.junit.Assert
import org.junit.Test

class LaunchesPageDomainMapperTest {

    @Test
    fun `map LaunchesPageDomain to LaunchesPresentation`() {

        val mockLaunchDomain = mockLaunch
        val expected = mockLaunchModel
        val actual = mockLaunch.toLaunchPresentation(mockLaunchDomain)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `map LaunchResponse to LaunchDomain`() {

        val expected = mockLaunch
        val actual = mockLaunchResponse.toLaunchDomain()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `map LinkResponse to LinkDomain`() {

        val expected = mockLink
        val actual = mockLinkResponse.toLinkDomain()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `map PatchResponse to PatchDomain`() {

        val expected = mockPatch
        val actual = mockPatchResponse.toPatchDomain()

        Assert.assertEquals(expected, actual)
    }
}
