package com.test.locationupdates.model.repository

import androidx.lifecycle.LiveData
import com.test.locationupdates.model.room.UserLocation
import com.test.locationupdates.model.room.UserLocationDao

class LocationRepo(private val locationDao: UserLocationDao) {

    fun getLocation(): LiveData<List<UserLocation>>  {
        return locationDao.getLocations()
    }

    suspend fun addLocation(location: UserLocation){
        locationDao.addLocation(location)
    }
}