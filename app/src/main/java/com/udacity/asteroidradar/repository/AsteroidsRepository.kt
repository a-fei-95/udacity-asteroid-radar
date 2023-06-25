package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.network.Network
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.utils.asDatabaseModel
import com.udacity.asteroidradar.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class AsteroidsRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = database.asteroidDatabaseDao.getAsteroids().map {
        it.asDomainModel()
    }.distinctUntilChanged()

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = Network.asteroidsService.getAsteroids(
                "2023-06-26",
                "2023-06-28",
                Constants.API_KEY
            ).await()
            database.asteroidDatabaseDao.insertAll(*asteroids.asDatabaseModel())
        }
    }
}