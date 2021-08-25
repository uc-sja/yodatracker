package com.test.locationupdates.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.locationupdates.model.repository.LocationRepo
import com.test.locationupdates.model.room.UserLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(private val repo: LocationRepo) : ViewModel() {
    fun getLocation(): LiveData<List<UserLocation>>{
        return repo.getLocation()
    }

    fun addLocation(location: UserLocation){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addLocation(location)
        }
    }
}