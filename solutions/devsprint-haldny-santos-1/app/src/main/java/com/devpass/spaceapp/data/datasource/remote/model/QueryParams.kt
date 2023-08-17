package com.devpass.spaceapp.data.datasource.remote.model

import com.squareup.moshi.Json

data class QueryParams(
    @Json(name = "options")
    val options: OptionsRequest
)

data class OptionsRequest(
    @Json(name = "sort")
    val sort: SortRequest,
    @Json(name = "limit")
    val limit: Int
)

data class SortRequest(
    @Json(name = "flight_number")
    val flightNumber: String
)
