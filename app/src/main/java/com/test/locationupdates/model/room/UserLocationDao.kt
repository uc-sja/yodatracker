package com.test.locationupdates.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//Define db operations

@Dao
interface UserLocationDao {

    @Query("SELECT * FROM  user_location ORDER BY date DESC")
    fun getLocations(): LiveData<List<UserLocation>>


    @Insert
    suspend fun addLocation(location: UserLocation)
}