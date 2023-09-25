package com.devpass.spaceapp.domain.model

data class LandpadsDetail(
    val name: String,
    val fullName: String,
    val status: String,
    val locality: String,
    val region: String,
    val latitude: String,
    val landingAttempts: Int,
    val landingSuccesses: Int,
    val details: String,
    val id: String
)
