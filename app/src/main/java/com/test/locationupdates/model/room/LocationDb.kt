package com.test.locationupdates.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DB_NAME = "location_db"

@TypeConverters(MyLocationTypeConverters::class)
@Database(entities = [UserLocation::class], version = 1)
abstract class LocationDb : RoomDatabase() {
    abstract fun locationDao(): UserLocationDao

    //for singleton initiation
    companion object{
        @Volatile private var INSTANCE: LocationDb ?= null

        fun getInstance(context: Context): LocationDb{
            if (INSTANCE == null){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context,
                            LocationDb::class.java,
                            DB_NAME
                        ).build()
                        return INSTANCE!!
                    }
                    return INSTANCE!!
                }
                }
            return INSTANCE!!

        }
    }
}