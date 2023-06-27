package com.udacity.asteroidradar.network.api

import com.udacity.asteroidradar.data.PictureOfDay
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    fun getAsteroids(
        @Query("api_key") apiKey: String
    ): Call<String>

    @GET("planetary/apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String
    ): PictureOfDay
}