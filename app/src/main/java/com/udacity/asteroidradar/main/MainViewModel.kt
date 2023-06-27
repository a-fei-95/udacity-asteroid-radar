package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.network.NetworkService
import com.udacity.asteroidradar.network.api.NasaApiStatus
import com.udacity.asteroidradar.repository.NasaRepository
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDatabase.getInstance(application)
    private val nasaRepository = NasaRepository(database)

    private val _status = MutableLiveData<NasaApiStatus>()
    val status: LiveData<NasaApiStatus>
        get() = _status

    private val _apod = MutableLiveData<PictureOfDay>()
    val apod: LiveData<PictureOfDay>
        get() = _apod

    private val _navigateToAsteroidDetail = MutableLiveData<Asteroid?>()
    val navigateToAsteroidDetail
        get() = _navigateToAsteroidDetail

    init {
        viewModelScope.launch {
            _status.value = NasaApiStatus.LOADING
            try {
                nasaRepository.refreshAsteroids()
                _apod.value = NetworkService.nasaApiService.getAPOD(Constants.API_KEY)
                Timber.e(_apod.value.toString())
                _status.value = NasaApiStatus.SUCCESS
            } catch (e: Exception) {
                Timber.e(e.message)
                _status.value = NasaApiStatus.ERROR
            }
        }
    }

    val asteroids = nasaRepository.asteroids

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToAsteroidDetail.value = asteroid
    }

    fun onAsteroidDetailsNavigated() {
        _navigateToAsteroidDetail.value = null
    }
}