package com.udacity.asteroidradar.main.viewholder

import com.udacity.asteroidradar.data.Asteroid

internal class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}