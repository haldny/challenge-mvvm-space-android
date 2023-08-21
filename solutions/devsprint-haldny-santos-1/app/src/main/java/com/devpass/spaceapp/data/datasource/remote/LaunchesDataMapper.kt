package com.devpass.spaceapp.data.datasource.remote

import com.devpass.spaceapp.data.datasource.remote.model.Launch
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel as LaunchPresentation

private const val SUCCESS = "Sucesso"
private const val FAILURE = "Falha"

fun Launch.toLaunchPresentation(launch: Launch) = LaunchPresentation(
    name = launch.name,
    number = launch.id,
    date = "null",
    status = if(launch.success) SUCCESS else FAILURE,
    image =  launch.links.patch.small
)
