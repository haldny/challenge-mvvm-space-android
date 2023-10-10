package com.devpass.spaceapp.data.mapper

import com.devpass.spaceapp.data.datasource.remote.mapper.toLaunchDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toLaunchesPageDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toLinkDomain
import com.devpass.spaceapp.data.datasource.remote.mapper.toPatchDomain
import com.devpass.spaceapp.data.mockLaunch
import com.devpass.spaceapp.data.mockLaunchResponse
import com.devpass.spaceapp.data.mockLaunchesPage
import com.devpass.spaceapp.data.mockLaunchesPageResponse
import com.devpass.spaceapp.data.mockLink
import com.devpass.spaceapp.data.mockLinkResponse
import com.devpass.spaceapp.data.mockPatch
import com.devpass.spaceapp.data.mockPatchResponse
import org.junit.Assert
import org.junit.Test

class LaunchesPageMapperTest {

    @Test
    fun `map LaunchesPageResponse to LaunchesPageDomain`() {

        val expected = mockLaunchesPage
        val actual = mockLaunchesPageResponse.toLaunchesPageDomain()

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
