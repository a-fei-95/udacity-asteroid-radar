package com.udacity.asteroidradar.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.data.Asteroid

class MainViewModel : ViewModel() {

    val asteroids = listOf(
        Asteroid(
            id = 1,
            codename = "XDR4093",
            closeApproachDate = "2023-04-12",
            absoluteMagnitude = 3.45,
            estimatedDiameter = 1003.2,
            relativeVelocity = 356.42,
            distanceFromEarth = 2134.2,
            isPotentiallyHazardous = true
        ),
        Asteroid(
            id = 2,
            codename = "FDR4093",
            closeApproachDate = "2022-05-12",
            absoluteMagnitude = 2.45,
            estimatedDiameter = 5003.2,
            relativeVelocity = 6.42,
            distanceFromEarth = 1134.2,
            isPotentiallyHazardous = true
        ),
        Asteroid(
            id = 3,
            codename = "XDR403",
            closeApproachDate = "2021-04-12",
            absoluteMagnitude = 3.435,
            estimatedDiameter = 31003.2,
            relativeVelocity = 56.42,
            distanceFromEarth = 22134.2,
            isPotentiallyHazardous = false
        ),
        Asteroid(
            id = 4,
            codename = "XR40sd93",
            closeApproachDate = "2013-06-12",
            absoluteMagnitude = 13.45,
            estimatedDiameter = 10043.2,
            relativeVelocity = 46.42,
            distanceFromEarth = 32134.2,
            isPotentiallyHazardous = false
        )
    )

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail
        get() = _navigateToAsteroidDetail

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetail.value = null
    }
}