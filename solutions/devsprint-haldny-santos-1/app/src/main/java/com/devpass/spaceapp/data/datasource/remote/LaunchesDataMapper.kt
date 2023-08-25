package com.devpass.spaceapp.data.datasource.remote

import com.devpass.spaceapp.data.datasource.remote.model.Launch
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel as LaunchPresentation

fun Launch.toLaunchPresentation(launch: Launch) = LaunchPresentation(
    name = launch.name,
    number = launch.id,
    date = "null",
    status = launch.success,
    image =  launch.links.patch.small
)
