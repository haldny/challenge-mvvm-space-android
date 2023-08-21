package com.devpass.spaceapp.data.datasource.remote.retrofit

import com.devpass.spaceapp.data.datasource.remote.api.SpaceXAPIService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.spacexdata.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    }

    fun getSpaceXAPI() = retrofit.create(SpaceXAPIService::class.java)
}