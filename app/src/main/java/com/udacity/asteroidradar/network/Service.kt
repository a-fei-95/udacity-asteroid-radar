package com.udacity.asteroidradar.network

import com.udacity.asteroidradar.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val asteroidsService: AsteroidsService = retrofit.create(AsteroidsService::class.java)
}