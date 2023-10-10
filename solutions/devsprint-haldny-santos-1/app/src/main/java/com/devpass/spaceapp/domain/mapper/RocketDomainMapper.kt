package com.devpass.spaceapp.domain.mapper

import com.devpass.spaceapp.domain.model.RocketsDetail as RocketsDomain
import com.devpass.spaceapp.presentation.rocket.RocketModel as RocketPresentation

fun RocketsDomain.toRocketPresentation() = RocketPresentation(
    name = name,
    description = description,
    flickrImages = flickrImages ?: emptyList(),
)
