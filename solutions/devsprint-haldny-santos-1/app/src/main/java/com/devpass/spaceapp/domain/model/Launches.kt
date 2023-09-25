package com.devpass.spaceapp.domain.model

data class Launch(
    val links: Link,
    val rocket: String,
    val success: Boolean,
    val name: String,
    val dateUtc: String?,
    val details: String?,
    val launchpad: String,
    val flightNumber: Int,
    val id: String
)

data class Link(
    val patch: Patch
)

data class Patch(
    val small: String
)
