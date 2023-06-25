package com.udacity.asteroidradar.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidsService {
    @GET("neo/rest/v1/feed")
    fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): Call<String>
}