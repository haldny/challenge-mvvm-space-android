package com.devpass.spaceapp.data.datasource.remote.model

import com.squareup.moshi.Json

data class RocketsDetail(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "flickr_images")
    val flickrImages: List<String>?
)
