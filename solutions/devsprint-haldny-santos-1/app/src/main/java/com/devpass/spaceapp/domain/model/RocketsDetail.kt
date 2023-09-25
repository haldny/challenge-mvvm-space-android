package com.devpass.spaceapp.domain.model

data class RocketsDetail(
    val id: String,
    val name: String,
    val description: String,
    val flickrImages: List<String>?
)
