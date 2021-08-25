package com.test.locationupdates.model.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "user_location")
data class UserLocation(
    @PrimaryKey(autoGenerate = true)
     var id: Int,
     var  longitude: String,
     var  latitude: String,
     var  date: Date = Date()
    ): Parcelable
