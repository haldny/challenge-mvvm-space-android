package com.devpass.spaceapp.data.datasource.remote.mapper

import com.devpass.spaceapp.data.datasource.remote.model.Launch
import com.devpass.spaceapp.data.datasource.remote.model.LaunchesPage
import com.devpass.spaceapp.data.datasource.remote.model.Link
import com.devpass.spaceapp.data.datasource.remote.model.Patch
import com.devpass.spaceapp.domain.model.Link as LinkDomain
import com.devpass.spaceapp.domain.model.Patch as PatchDomain
import com.devpass.spaceapp.domain.model.LaunchesPage as LaunchesPageDomain
import com.devpass.spaceapp.domain.model.Launch as LaunchDomain

fun LaunchesPage.toLaunchesPageDomain() = LaunchesPageDomain(
    docs = docs.map {
        it.toLaunchDomain()
    },
    totalDocs = totalDocs,
    limit = limit,
    totalPages = totalPages,
    page = page,
    pagingCounter = pagingCounter,
    hasPrevPage = hasPrevPage,
    hasNextPage = hasNextPage,
    prevPage = prevPage,
    nextPage = nextPage
)

fun Launch.toLaunchDomain() = LaunchDomain(
    links = links.toLinkDomain(),
    rocket = rocket,
    name = name,
    dateUtc = dateUtc,
    success = success,
    details = details,
    launchpad = launchpad,
    flightNumber = flightNumber,
    id = id
)

fun Link.toLinkDomain() = LinkDomain(
    patch = patch.toPatchDomain()
)

fun Patch.toPatchDomain() = PatchDomain(
    small = small
)


