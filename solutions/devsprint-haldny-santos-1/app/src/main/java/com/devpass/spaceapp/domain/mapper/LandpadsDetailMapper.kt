package com.devpass.spaceapp.domain.mapper

import com.devpass.spaceapp.data.datasource.remote.model.LandpadsDetail
import com.devpass.spaceapp.domain.model.LandpadsDetail as LandpadsDetailDomain

fun LandpadsDetail.toLandpadsDetailDomain() = LandpadsDetailDomain(
    name = name,
    fullName = fullName,
    status = status,
    locality = locality,
    region = region,
    latitude = latitude,
    landingAttempts = landingAttempts,
    landingSuccesses = landingSuccesses,
    details = details,
    id = id
)
