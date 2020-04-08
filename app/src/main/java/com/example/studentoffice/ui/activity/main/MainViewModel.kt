package com.example.studentoffice.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentoffice.model.App
import com.example.studentoffice.model.LocationState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class MainViewModel : ViewModel() {
    private val _localResponse = MutableLiveData<LocationState>().apply {}
    val localResponse: LiveData<LocationState> = _localResponse
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(App.getAppContext())

    private fun createRequest(): LocationRequest {
        return LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000L)
            .setFastestInterval(5 * 1000L)
            .setMaxWaitTime(10 * 1000L)
    }

    fun requestLocation() {
        _localResponse.value = LocationState(true, null)
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if (locationResult == null) {
                    return
                }
                locationResult.locations.forEach {
                    if (it == null) {
                        return
                    }
                    fusedLocationProviderClient.removeLocationUpdates(this)
                    _localResponse.value = LocationState(false, it)
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(createRequest(), locationCallback, null)
    }
}