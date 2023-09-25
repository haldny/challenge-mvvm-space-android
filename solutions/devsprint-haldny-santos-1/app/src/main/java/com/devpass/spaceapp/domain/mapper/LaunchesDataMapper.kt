package com.devpass.spaceapp.domain.mapper

import com.devpass.spaceapp.domain.model.Launch
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel

fun Launch.toLaunchPresentation(launch: Launch) = LaunchModel(
    name = launch.name,
    number = launch.flightNumber.toString(),
    date = "null",
    status = launch.success,
    image =  launch.links.patch.small,
    details = launch.details.orEmpty(),
    rocketId = launch.rocket
)
