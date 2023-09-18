package com.devpass.spaceapp.data.datasource.remote.mapper

import com.devpass.spaceapp.data.datasource.remote.model.Launch
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel as LaunchPresentation

fun Launch.toLaunchPresentation(launch: Launch) = LaunchPresentation(
    name = launch.name,
    number = launch.flightNumber.toString(),
    date = "null",
    status = launch.success,
    image =  launch.links.patch.small,
    details = launch.details.orEmpty(),
    rocketId = launch.rocket
)
