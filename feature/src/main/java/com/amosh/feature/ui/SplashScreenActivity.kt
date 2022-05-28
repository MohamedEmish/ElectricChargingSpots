package com.amosh.feature.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.amosh.common.AppMessage
import com.amosh.common.addIfNotExistAndNotNull
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.SettingsClient
import com.tapadoo.alerter.Alerter

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val requestLocationPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.entries.isPermissionsGranted()) {
                checkGpsSettings()
            }
        }

    private val gpsIntentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                openMainActivity()
            } else {
                showAppMessage(
                    AppMessage(
                        AppMessage.FAILURE,
                        "Please allow GPS permission and restart app"
                    )
                )
            }
        }

    private lateinit var settingsClient: SettingsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLocationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun <K, V> MutableSet<MutableMap.MutableEntry<K, V>>.isPermissionsGranted(): Boolean {
        val errorMessage: MutableList<String> = mutableListOf()
        var granted = true
        this.forEach {
            if (it.value == false) {
                granted = false
                errorMessage.addIfNotExistAndNotNull(
                    when (it.key) {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        -> "Missing Location Permission, please allow it, and restart the app"
                        else -> "Missing Permission"
                    }
                )
            }
        }
        if (!granted) {
            showAppMessage(
                AppMessage(
                    AppMessage.FAILURE,
                    errorMessage.joinToString(separator = "\n")
                )
            )
        }
        return granted
    }

    private fun showAppMessage(message: AppMessage, duration: Long = 3000L) {
        Alerter.create(this)
            .setDuration(duration)
            .setBackgroundColorRes(message.getMessageColor())
            .setText(message.message)
            .setTextAppearance(com.amosh.base.R.style.AppMessageStyle)
            .hideIcon()
            .show()
    }

    private fun checkGpsSettings() {
        val locationRequest = LocationRequest.create().apply {
            interval = 50000
            fastestInterval = 50000
            smallestDisplacement = 1000f // 1 km
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        }

        val locationSettingsRequest =
            LocationSettingsRequest.Builder().apply {
                addLocationRequest(locationRequest)
            }.build()

        settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnFailureListener(this) { e: Exception ->
                when ((e as ApiException).statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the
                            // result in onActivityResult().
                            val rae = e as ResolvableApiException
                            val intentSenderRequest = IntentSenderRequest.Builder(rae.resolution.intentSender).build()
                            gpsIntentResultLauncher.launch(
                                intentSenderRequest
                            )
                        } catch (sie: IntentSender.SendIntentException) {
                            sie.printStackTrace()
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }.addOnSuccessListener {
                openMainActivity()
            }
    }

    private fun openMainActivity() {
        startActivity(
            Intent(this@SplashScreenActivity, MainActivity::class.java)
        )
        finish()
    }
}