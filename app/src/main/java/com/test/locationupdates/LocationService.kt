package com.test.locationupdates

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompatExtras
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*
import com.test.locationupdates.model.repository.LocationRepo
import com.test.locationupdates.model.room.LocationDb
import com.test.locationupdates.model.room.UserLocation
import com.test.locationupdates.model.room.UserLocationDao
import com.test.locationupdates.viewmodel.LocationViewModel
import com.test.locationupdates.viewmodel.LocationViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class LocationService: Service() {

    private lateinit var locationRepo: LocationRepo
    private lateinit var locationDao: UserLocationDao
    private lateinit var locationViewModel: LocationViewModel
    private val NOTIFICATION_ID = 123456
    private lateinit var notificationManager: NotificationManager
    private val NOTIFICATION_CHANNEL_ID = "10332"
    private val TAG = "LocationService"

    private lateinit var locationRequest1: LocationRequest
    private lateinit var locationRequest2: LocationRequest
    private lateinit var locationCallback1: LocationCallback
    private lateinit var locationCallback2: LocationCallback
    private lateinit var fusedLocationClient1: FusedLocationProviderClient
    private lateinit var fusedLocationClient2: FusedLocationProviderClient

    private lateinit var job : Job
    private lateinit var scope : CoroutineScope

    companion object{
        var requestingLocation = false
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate() {

        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        job = SupervisorJob()
        scope = CoroutineScope(Dispatchers.Main + job)

        locationDao = LocationDb.getInstance(applicationContext).locationDao()
        locationRepo = LocationRepo(locationDao)


        createLocationRequest()

        fusedLocationClient1 = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient2 = LocationServices.getFusedLocationProviderClient(this)

        locationCallback1 = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                requestingLocation  = true
                var location = locationResult.lastLocation

                updateLocation(location)
            }
        }

        locationCallback2 = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                requestingLocation  = true
                val location = locationResult.lastLocation
                updateLocation(location)
            }
        }

    }

    private fun updateLocation(location: Location) {

        notificationManager.notify(NOTIFICATION_ID, createNotification(""+location.latitude+"  "+location.longitude))

        val userLocation = UserLocation(0, location.latitude.toString(), location.longitude.toString())

        //the life of the scope is until onDestroy gets called
        scope.launch() {
            locationRepo.addLocation(userLocation)
        }


    }

    private fun createLocationRequest(){

        //Simply Fetching the location at the  periodic 30s interval
        locationRequest1 = LocationRequest.create()?.apply {
            interval = 30000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }!!



        //with the help of locationrequest2, at worst system will check for 200 displacement
        //every 5s (interval) and at best it will check at every 1 second (fastest interval)
        //Hence battery consumption is kept in mind

        locationRequest2 = LocationRequest.create()?.apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 5000
            fastestInterval = 1000
            smallestDisplacement = 200f
        }!!
    }


    private fun stopLocationUpdates() {
        
        fusedLocationClient1.removeLocationUpdates(locationCallback1)
        fusedLocationClient2.removeLocationUpdates(locationCallback2)

        requestingLocation = false

        val intent = Intent("isRequestingLocation")
        intent.putExtra("status", false)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d(TAG, "startLocationUpdates: no permit")
            return
        }


        //to update button state
        val intent = Intent("isRequestingLocation")
        intent.putExtra("status", true)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)


        fusedLocationClient1.requestLocationUpdates(locationRequest1, locationCallback1, Looper.getMainLooper())
        fusedLocationClient2.requestLocationUpdates(locationRequest2, locationCallback2, Looper.getMainLooper())
    }



    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onCreate: yay")
        val action = intent?.action

        if(action == "start"){
            if(requestingLocation){
                Log.d(TAG, "onStartCommand: Already Started")
                return START_NOT_STICKY
            }
            val notification = createNotification("Location Updates Started")
            startForeground(NOTIFICATION_ID, notification)
            startLocationUpdates()
            return START_NOT_STICKY
        }

        if(action == "stop"){
            stopLocationUpdates()
            stopForeground(true)
            stopSelf()
        }

        return START_REDELIVER_INTENT
    }

    private fun createNotification(notificationText: String) : Notification {

        val notificationContent = notificationText
        val notificationTitle = "Yoda Assignment"

        //Create notification channel for api level 26+
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                notificationTitle,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(notificationChannel)

        }

        val notificationCompatBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        //flags for not restarting activity if already opened
        val launchActivity = Intent(this, MainActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val mainActivityPendingIntent = PendingIntent.getActivity(this,
        0, launchActivity, 0)



        return notificationCompatBuilder
            .setContentTitle(notificationTitle)
            .setContentText(notificationContent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setOngoing(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(mainActivityPendingIntent)
            .build()

    }
}