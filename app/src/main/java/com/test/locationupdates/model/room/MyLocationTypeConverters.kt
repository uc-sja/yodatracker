package com.test.locationupdates.model.room

import androidx.room.TypeConverter
import java.util.*

// * Converts non-standard objects in the {@link MyLocation} data class into and out of the database.
class MyLocationTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

}
