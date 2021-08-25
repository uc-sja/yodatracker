package com.test.locationupdates

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.test.locationupdates.databinding.ActivityMainBinding
import com.test.locationupdates.model.repository.LocationRepo
import com.test.locationupdates.model.room.LocationDb
import com.test.locationupdates.model.room.UserLocation
import com.test.locationupdates.viewmodel.LocationViewModel
import com.test.locationupdates.viewmodel.LocationViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


/*
The following app continuously captures the user's current location every 30 secs or
 when the user moves more than 200 metres. Data is be stored in the app local database via Room.
  There is also  a view on the screen which shows the number of data uploads on the db today and last
updated time.

I have user foreground service as it is more trusted resource when it comes to location tracking.
Another way was via Job scheduler API and finding location in the background but it is less
reliable since the android system may kill background services more frequently than foreground
 */


class MainActivity : AppCompatActivity() {
    private val LOCATION_REQUEST_PERMISSION_CODE: Int = 2214
    private val TAG = "MainActivity"
    private var count = 0;
    private lateinit var locationViewModel: LocationViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.startUpdates.setOnClickListener(View.OnClickListener {

            if(LocationPermissionsGranted()){
                startLocationTracking()

            } else {
                requestLocationPermissions()
            }
        })

        binding.stopUpdates.setOnClickListener(View.OnClickListener {
            val intent  = Intent(this, LocationService::class.java)
            intent.action = "stop"
            startService( intent)

        })

        val locationDao = LocationDb.getInstance(applicationContext).locationDao()
        val locationRepo = LocationRepo(locationDao)

        locationViewModel = ViewModelProvider(this, LocationViewModelFactory(locationRepo))
            .get(LocationViewModel::class.java)


        locationViewModel.getLocation().observe(this, Observer {
            if(it.isNotEmpty()){

                val todays_uploads = it.filter {
                    DateUtils.isToday(it.date.time)
                }

                binding.dbUploads.text = ""+todays_uploads.size

                val dateFormatter = SimpleDateFormat("MMM dd,yyyy HH:mm:ss")

                val lastLocation = it.first()

                binding.lastLocation.text = lastLocation.latitude+", "+lastLocation.longitude
                binding.lastUpdated.text = dateFormatter.format(lastLocation.date)

            }
        })
    }

    private fun startLocationTracking() {
        val intent  = Intent(this, LocationService::class.java)
        intent.action = "start"
        startService( intent)
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this,
        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_PERMISSION_CODE
            )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_REQUEST_PERMISSION_CODE){
            if(grantResults.isEmpty()){
                Toast.makeText(this, "User interaction was interrupted", Toast.LENGTH_SHORT)
            } else if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startLocationTracking()
            } else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                    Snackbar.make(
                        binding.parentContainer,
                        R.string.permissionRationale,
                        Snackbar.LENGTH_LONG
                    ).setAction(R.string.ok){
                        requestLocationPermissions()
                    }.show()
                } else{
               //If user denies multiple times, then only option is to enable from settings
                    Snackbar.make(
                        binding.parentContainer,
                        R.string.permissionRationale,
                        Snackbar.LENGTH_LONG
                    ).setAction(R.string.settings){
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(
                            "package",
                            BuildConfig.APPLICATION_ID,
                            null
                        )
                        intent.data = uri
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }.show()
                }
            }
        }
    }

    private fun LocationPermissionsGranted(): Boolean {
        val requestStatus = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION)
        return requestStatus
    }


    override fun onResume() {
        super.onResume()

        //Since this is a test project, I applied a workaround to manage btn state
        //Otherwise in a production app I would have used bound services to manage
        //button state on Bind, Rebind and Unbind functions

        updateBtnState(LocationService.requestingLocation)
        LocalBroadcastManager.getInstance(this).registerReceiver(LocationBroadCastReceiver(), IntentFilter("isRequestingLocation"))
    }

    override fun onPause()
    {
        super.onPause()
        updateBtnState(LocationService.requestingLocation)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(LocationBroadCastReceiver())
    }


    inner class LocationBroadCastReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive: location ")
            if("isRequestingLocation" == intent?.action ){
                    val requestingLocation = intent.getBooleanExtra("status", false)
                    updateBtnState(requestingLocation)
            }
        }
    }

    private fun updateBtnState(requestingLocation: Boolean) {
        if(requestingLocation){
            binding.startUpdates.isEnabled = false
            binding.stopUpdates.isEnabled = true
        } else{
            binding.startUpdates.isEnabled = true
            binding.stopUpdates.isEnabled = false

        }
    }


}