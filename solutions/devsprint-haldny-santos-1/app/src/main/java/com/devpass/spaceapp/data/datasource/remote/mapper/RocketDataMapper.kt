package com.devpass.spaceapp.data.datasource.remote.mapper

import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail
import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDomain

fun RocketsDetail.toRocketDomain() = RocketsDomain(
    id = id,
    name = name,
    description = description,
    flickrImages = flickrImages
)