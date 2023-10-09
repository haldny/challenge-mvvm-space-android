package com.devpass.spaceapp.data

import com.devpass.spaceapp.data.datasource.remote.model.LandpadsDetail as LandpadsDetailResponse
import com.devpass.spaceapp.data.datasource.remote.model.Launch as LaunchResponse
import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage as LaunchesPageResponse
import com.devpass.spaceapp.data.datasource.remote.model.Link as LinkResponse
import com.devpass.spaceapp.data.datasource.remote.model.OptionsRequest
import com.devpass.spaceapp.data.datasource.remote.model.Patch as PatchResponse
import com.devpass.spaceapp.data.datasource.remote.model.QueryParams
import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail as RocketsDetailResponse
import com.devpass.spaceapp.data.datasource.remote.model.SortRequest
import com.devpass.spaceapp.domain.model.LandpadsDetail
import com.devpass.spaceapp.domain.model.Launch
import com.devpass.spaceapp.domain.model.LaunchesPage
import com.devpass.spaceapp.domain.model.Link
import com.devpass.spaceapp.domain.model.Patch
import com.devpass.spaceapp.domain.model.RocketsDetail
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import com.devpass.spaceapp.presentation.rocket.RocketModel


val mockIdParam = "id"

val mockQueryParams = QueryParams(
    options = OptionsRequest(
        sort = SortRequest(
            flightNumber = "asc"
        ),
        limit = 10
    )
)


val mockPatchResponse = PatchResponse(
    small = "image"
)
val mockLinkResponse = LinkResponse(
    patch = mockPatchResponse
)

val mockLaunchResponse = LaunchResponse(
    links = mockLinkResponse,
    rocket = "id",
    success = true,
    name = "name",
    dateUtc = "null",
    details = "details",
    launchpad = "launchpad",
    flightNumber = 1,
    id = "id"
)

val mockLaunchesPageResponse = LaunchesPageResponse(
    docs = listOf(mockLaunchResponse),
    totalDocs = 1,
    limit = 1,
    totalPages = 1,
    page = 1,
    pagingCounter = 1,
    hasPrevPage = true,
    hasNextPage = true,
    prevPage = null,
    nextPage = null,
)

val mockPatch = Patch(
    small = "image"
)
val mockLink = Link(
    patch = mockPatch
)

val mockLaunch = Launch(
    links = mockLink,
    rocket = "id",
    success = true,
    name = "name",
    dateUtc = "null",
    details = "details",
    launchpad = "launchpad",
    flightNumber = 1,
    id = "id"
)

val mockLaunchesPage = LaunchesPage(
    docs = listOf(mockLaunch),
    totalDocs = 1,
    limit = 1,
    totalPages = 1,
    page = 1,
    pagingCounter = 1,
    hasPrevPage = true,
    hasNextPage = true,
    prevPage = null,
    nextPage = null,
)

val mockRocketsDetailResponse = RocketsDetailResponse(
    id = "id",
    name = "name",
    description = "description",
    flickrImages = listOf("flickrImages")
)

val mockRocketsDetail = RocketsDetail(
    id = "id",
    name = "name",
    description = "description",
    flickrImages = listOf("flickrImages")
)

val mockLandpadsDetailResponse = LandpadsDetailResponse(
    id = "",
    name = "",
    fullName = "",
    status = "",
    locality = "",
    region = "",
    latitude = "",
    landingSuccesses = 0,
    landingAttempts = 0,
    details = ""
)

val mockLandpadsDetail = LandpadsDetail(
    id = "",
    name = "",
    fullName = "",
    status = "",
    locality = "",
    region = "",
    latitude = "",
    landingSuccesses = 0,
    landingAttempts = 0,
    details = ""
)

val mockRocketModel = RocketModel(
    name = "name",
    description = "description",
    flickrImages = listOf("flickrImages")
)

val mockLaunchModel = LaunchModel(
    name = "name",
    number = "1",
    date = "null",
    status = true,
    details = "details",
    image = "image",
    rocketId = "id"
)