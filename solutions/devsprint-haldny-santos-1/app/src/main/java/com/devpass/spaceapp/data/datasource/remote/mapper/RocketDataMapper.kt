package com.devpass.spaceapp.data.datasource.remote.mapper

import com.devpass.spaceapp.data.datasource.remote.model.RocketsDetail
import com.devpass.spaceapp.presentation.rocket.RocketModel as RocketPresentation

fun RocketsDetail.toRocketPresentation() = RocketPresentation(
    name = name,
    description = description,
    flickrImages = flickrImages ?: emptyList(),
)
