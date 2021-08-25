package com.test.locationupdates.application

import android.app.Application
import android.content.Context

class MainApp : Application() {

    companion object{
         private var appContext: Context? = null
        fun getContext():Context{
            return appContext!!
        }
    }

    init {
        appContext = this
    }
}