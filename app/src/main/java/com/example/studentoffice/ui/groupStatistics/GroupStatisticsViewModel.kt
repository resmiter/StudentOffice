package com.example.studentoffice.ui.groupStatistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GroupStatisticsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is group statistics Fragment"
    }
    val text: LiveData<String> = _text

//    fun getLocation(onSuccess: (Location) -> Unit) {
//        locationClient.receiveLocationUpdates(object: LocationUpdatesCallback {
//
//            override fun onReceveLocation(location: Location){
//                onSuccess(location)
//            }
//        })
//    }
//
//    fun doSmth(){
//        getLocation {
//            liveData.value = LocationState(loading = false, location = it)
//        }
//    }
}