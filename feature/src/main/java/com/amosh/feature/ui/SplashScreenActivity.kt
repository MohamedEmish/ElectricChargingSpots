package com.amosh.feature.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.result.contract.ActivityResultContracts
import com.amosh.common.AppMessage
import com.amosh.common.addIfNotExistAndNotNull
import com.amosh.feature.R
import com.tapadoo.alerter.Alerter

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val requestLocationPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.entries.isPermissionsGranted()) {
                startActivity(
                    Intent(this@SplashScreenActivity, MainActivity::class.java)
                )
                finish()
            }
        }

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
}